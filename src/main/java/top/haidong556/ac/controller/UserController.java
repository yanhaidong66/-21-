package top.haidong556.ac.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.role.User;
import org.springframework.stereotype.Controller;
import top.haidong556.ac.security.MyUserDetailsServiceImpl;
import top.haidong556.ac.service.AcService;

@Controller
@RequestMapping("/user")
public class UserController {

    private AcService acService;
    private MyUserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(AcService acService,MyUserDetailsServiceImpl userDetailsService){
        this.userDetailsService=userDetailsService;
        this.acService=acService;
    }
    @GetMapping()
    public ModelAndView getUserPage(){
        User user = userDetailsService.currentUser();
        int acId = user.getAcId();
        Ac acState = acService.getAcState(acId);
        ModelAndView modelAndView=new ModelAndView("user");
        modelAndView.addObject("acState",acState);
        modelAndView.addObject("user",user);
        System.out.println(acState);
        return modelAndView;
    }
    @GetMapping("/temp")
    public ModelAndView changeAcTemp( int newTemp){
        User user = userDetailsService.currentUser();
        int acId = user.getAcId();
        acService.changeAcTemp(acId,newTemp,userDetailsService.currentUser().getUserId());
        Ac acState = acService.getAcState(acId);
        ModelAndView modelAndView=new ModelAndView("user");
        modelAndView.addObject("acState",acState);
        return modelAndView;
    }

    @GetMapping("/windSpeed")
    public ModelAndView changeAcWindSpeed( int newWindSpeed){
        int windSpeed=Integer.valueOf(newWindSpeed);
        User user = userDetailsService.currentUser();
        int acId = user.getAcId();
        acService.changeAcWindSpeed(acId,windSpeed,userDetailsService.currentUser().getUserId());
        Ac acState = acService.getAcState(acId);
        ModelAndView modelAndView=new ModelAndView("user");
        modelAndView.addObject("acState",acState);
        return modelAndView;
    }
    @GetMapping("/close")
    public ModelAndView close(){
        User user = userDetailsService.currentUser();
        int acId = user.getAcId();
        acService.closeAc(acId,userDetailsService.currentUser().getUserId());
        Ac acState = acService.getAcState(acId);
        ModelAndView modelAndView=new ModelAndView("user");
        modelAndView.addObject("acState",acState);
        return modelAndView;
    }
    @GetMapping("/open")
    public ModelAndView open(){
        User user = userDetailsService.currentUser();
        int acId = user.getAcId();
        acService.openAc(acId,userDetailsService.currentUser().getUserId());
        Ac acState = acService.getAcState(acId);
        ModelAndView modelAndView=new ModelAndView("user");
        modelAndView.addObject("acState",acState);
        return modelAndView;
    }




}
