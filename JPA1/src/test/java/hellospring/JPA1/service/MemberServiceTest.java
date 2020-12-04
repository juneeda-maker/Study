package hellospring.JPA1.service;

import hellospring.JPA1.domain.Address;
import hellospring.JPA1.domain.Member;
import hellospring.JPA1.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @Test
    public void join() throws Exception {
        //given
        Member member = new Member();
        member.setId("1");
        member.setPwd("1");
        member.setPwd2("1");
        member.setName("1");



        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    /*
    @Test
    public void duplicateMemberException() throws Exception {

        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("error should occur.");

    }
*/
}