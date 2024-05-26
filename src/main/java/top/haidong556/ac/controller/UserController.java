package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import top.haidong556.ac.entity.role.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.haidong556.ac.service.UserService;

@Controller
@RequestMapping
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(){};

    @PostMapping("/user")
    public void createUser(User user){
        userService.createUser();
    }
    @DeleteMapping()
    public void deleteUser(int userId){
        userService.deleteUser();
    }

}
