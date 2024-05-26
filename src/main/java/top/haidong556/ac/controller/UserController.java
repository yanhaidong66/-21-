package top.haidong556.ac.controller;

import top.haidong556.ac.entity.role.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {

    @PostMapping("/user")
    public void createUser(User user){

    }
    @DeleteMapping()
    public void deleteUser(int userId){

    }

}
