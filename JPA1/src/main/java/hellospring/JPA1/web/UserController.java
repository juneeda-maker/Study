package hellospring.JPA1.web;

import hellospring.JPA1.domain.User;
import hellospring.JPA1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> list() {
        List<User> users = userService.getUsers();
        return null;
    }

    //1. User list
    //2. User create
    //3. User update
    //4. User delete
    //(1: customer, 2:restaurant owner, 3:admin)
}
