package hellospring.JPA1.exception;

public class PasswordWrongException extends RuntimeException {
    PasswordWrongException() {
        super("Password is wrong");
    }
}
