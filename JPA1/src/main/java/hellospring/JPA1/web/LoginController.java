package hellospring.JPA1.web;

import hellospring.JPA1.config.LoginRepository;
import hellospring.JPA1.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class LoginController {

    private final LoginRepository loginRepository;



   // @GetMapping("/")
   // public String index(){
    //    return "/home";
    //}


    //로그인 관련 기능

    @GetMapping("/members/login")
    public String loginForm(){

        return "members/loginMemberForm";
    }








    /*
    @PostMapping("/members/login")
    public String login(String id, String pwd){

        System.out.println("id : , pwd : ");
        Member member = this.login.findMember(id, pwd);
        if(member !=null){
            return "members/loginOK";
        }
        return "members/loginFail";
    }
    */


    /*
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

     */

    //로그아웃 관련 기능
    @GetMapping("members/logout")
    public String logout(HttpSession session){
        session.removeAttribute("id");
        System.out.print("@@@ logout success @@@@");
        return "redirect:/";
    }

}
