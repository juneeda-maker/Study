package hellospring.JPA1.repository;

import hellospring.JPA1.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long number) {
        return em.find(Member.class, number);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Member> Login(String id, String pwd){
        return em.createQuery("select m from Member m where m.id = :id and m.pwd = :pwd", Member.class)
                .setParameter("id", id)
                .setParameter("pwd", pwd)
                .getResultList();
    }
}