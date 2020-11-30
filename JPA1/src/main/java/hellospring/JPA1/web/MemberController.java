package hellospring.JPA1.web;


import hellospring.JPA1.domain.Address;
import hellospring.JPA1.domain.Member;
import hellospring.JPA1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;





    @GetMapping("/members/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "members/loginMemberForm";
    }

    @PostMapping("/members/login")
    public String login(Model model, HttpServletRequest req, HttpSession session) {
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        Member member = new Member();
        member.setId(id);
        member.setPwd(pwd);
        boolean logo = memberService.logingo(member);

        if (logo == true) {
            System.out.println("로그인 성공!");
            session.setAttribute("id", id);

        } else {
            System.out.println("로그인 실패");
            session.setAttribute("id", null);
        }


        return "redirect:/";
    }





    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        //@vaild 유효성 검사를 위한 어노테이션.


        if(result.hasErrors()){
            return "members/createMemberForm";
        }


        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();

        member.setId(form.getId());
        member.setPwd(form.getPwd());
        member.setPwd2(form.getPwd2());
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}



