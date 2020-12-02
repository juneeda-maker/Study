package hellospring.JPA1.config;

import hellospring.JPA1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where id = :id and pwd = :pwd")
    Member findMember(String id, String pwd);
}
