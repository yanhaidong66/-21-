package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.service.AcService;
import top.haidong556.ac.service.UserService;

@Controller
@RequestMapping("waiter")
public class WaiterController {
    private UserService userService;
    private AcService acService;

    @Autowired
    public WaiterController(UserService userService){
        this.userService=userService;
    }
    @GetMapping("/addUser")
    public ModelAndView addUserPage(){
        ModelAndView modelAndView = new ModelAndView("addUser");
        return modelAndView;
    }
    @PostMapping("/addUser")
    public void addUser(){


    }
    @GetMapping("/checkout")
    public ModelAndView checkoutPage(){
        ModelAndView modelAndView=new ModelAndView("checkout");
        return modelAndView;
    }
    @PostMapping("/checkout")
    public void checkout(int userId){
        userService.deleteUser(userId);
    }
    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelAndView=new ModelAndView("waiter");
        return modelAndView;
    }
}
