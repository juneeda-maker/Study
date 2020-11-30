package hellospring.JPA1.domain;

import lombok.Data;

@Data
public class SessionRequestDto {

    private String email;

    private String password;
}
