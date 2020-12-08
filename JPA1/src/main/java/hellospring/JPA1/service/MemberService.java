package hellospring.JPA1.service;


import hellospring.JPA1.config.LoginRepository;
import hellospring.JPA1.domain.Member;
import hellospring.JPA1.domain.Role;
import hellospring.JPA1.dto.MemberDto;
import hellospring.JPA1.repository.MemberRepository;
import hellospring.JPA1.web.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {


    private final MemberRepository memberRepository;
    private final LoginRepository loginRepository;






    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검사
        validateDuplicateName(member); //닉네임 중복 검사
        validatePwdMember(member);
        memberRepository.save(member);
        return member.getNumber();
    }


    //아이디 중복검사
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //닉네임  중복검사
    private void validateDuplicateName(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 사용중인 이름 입니다.");
        }

    }

    //비밀번호 확인
    private void validatePwdMember(Member member) {
        String pwdCheck = member.getPwd();


        if (!pwdCheck.equals(member.getPwd2())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

    }


    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    //멤버 존재 여부 확인
    public boolean logingo(Member member) {

        boolean check = false;


        List<Member> sql = memberRepository.Login(member.getId(), member.getPwd());


        if (!sql.isEmpty()) {
            System.out.println("존재");
            check = true;
        } else {
            System.out.println("실패");
        }

        return check;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> memberWrapper = loginRepository.findById(id);
        Member member = memberWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(id)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(member.getId(), member.getPwd(), authorities);
    }






}