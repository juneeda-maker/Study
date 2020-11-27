package hellospring.JPA1.service;


import hellospring.JPA1.domain.Member;
import hellospring.JPA1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;


    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검
        validatePwdMember(member);
        memberRepository.save(member);
        return member.getNumber();
    }


    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            //test
            //tt
            //tt
            //tt
            //tt
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

}