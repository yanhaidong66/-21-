package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.entity.role.Waiter;
import top.haidong556.ac.security.MyUserDetailsServiceImpl;
import top.haidong556.ac.service.AcService;
import top.haidong556.ac.service.UserService;

import java.util.List;

@Controller
@RequestMapping("waiter")
public class WaiterController {
    private UserService userService;
    private AcService acService;
    private MyUserDetailsServiceImpl userDetailsService;

    @Autowired
    public WaiterController(UserService userService,AcService acService,MyUserDetailsServiceImpl userDetailsService){
        this.userService=userService;
        this.acService=acService;
        this.userDetailsService=userDetailsService;
    }

    @GetMapping("/checkin")
    public ModelAndView checkinPage(){
        ModelAndView modelAndView = new ModelAndView("waiter-checkin");
        Waiter waiter = userDetailsService.currentWaiter();
        modelAndView.addObject("waiter",waiter);
        return modelAndView;
    }
    @PostMapping("/checkin")
    public ModelAndView checkin(String username,String password,int roomId){
        User user=new User(username,password);
        userService.createUser(user);
        ModelAndView modelAndView = new ModelAndView("waiter-checkin");
        Waiter waiter = userDetailsService.currentWaiter();
        modelAndView.addObject("waiter",waiter);
        return modelAndView;
    }


    @GetMapping("/checkout")
    public ModelAndView checkoutPage(){
        ModelAndView modelAndView=new ModelAndView("waiter-checkout");

        Waiter waiter = userDetailsService.currentWaiter();
        modelAndView.addObject("waiter",waiter);
        return modelAndView;

    }
    @GetMapping("/allUser")
    public ModelAndView allUserPage(){
        ModelAndView modelAndView=new ModelAndView("waiter-allUser");
        Waiter waiter = userDetailsService.currentWaiter();
        modelAndView.addObject("waiter",waiter);
        List<User> allUser = userService.getAllUser();
        modelAndView.addObject("allUser",allUser);
        return modelAndView;
    }
}
