package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.role.People;
import top.haidong556.ac.entity.role.User;
import org.springframework.stereotype.Controller;
import top.haidong556.ac.service.AcService;
import top.haidong556.ac.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private AcService acService;
    @Autowired
    public UserController(AcService acService){
        this.acService=acService;
    }
    @GetMapping()
    public ModelAndView getUserPage(){
        ModelAndView modelAndView=new ModelAndView("user");
        return modelAndView;
    }
    @PatchMapping("/temp")
    public ModelAndView changeAcTemp(int acId, int newTemp){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        acService.changeAcTemp(acId,newTemp);
        return modelAndView;
    }

    @PatchMapping("/windSpeed")
    public void changeAcWindSpeed(int acId,int newWindSpeed){
        acService.changeAcWindSpeed(acId,newWindSpeed);
    }
    @GetMapping("/close")
    public void closeAc(int acId){
        acService.closeAc(acId);
    }
    @GetMapping("/open")
    public void openAc(int acId){
        acService.openAc(acId);
    }
    @GetMapping()
    public ModelAndView getAcState(){
        Ac acState = acService.getAcState();
        ModelAndView modelAndView=new ModelAndView("ac");
        modelAndView.addObject("acState",acState);
        return modelAndView;
    }


}
