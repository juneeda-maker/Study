package hellospring.JPA1.config;

import hellospring.JPA1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id);
}
