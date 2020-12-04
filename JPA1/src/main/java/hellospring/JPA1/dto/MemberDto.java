package hellospring.JPA1.dto;

import hellospring.JPA1.domain.Address;
import hellospring.JPA1.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString

public class MemberDto {
    private Long number;
    private String id;
    private String pwd;
    private String name;
    private Address address;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;


}
