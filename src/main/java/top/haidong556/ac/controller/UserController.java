package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import top.haidong556.ac.entity.role.People;
import top.haidong556.ac.entity.role.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.haidong556.ac.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(){};

    @PostMapping()
    public void createUser(User user){
        userService.createUser(user);
    }
    @DeleteMapping()
    public void deleteUser(int userId){
        userService.deleteUser(userId);
    }
    @GetMapping()
    public void getUser(int userId){
        People user = userService.getUser(userId);
    }
    @GetMapping("/all")
    public void getAllUser(){
        List<User> allUser = userService.getAllUser();
    }

}
