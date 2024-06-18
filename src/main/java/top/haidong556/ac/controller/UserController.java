package top.haidong556.ac.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.annotations.RangeValidation;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.role.User;
import org.springframework.stereotype.Controller;
import top.haidong556.ac.security.MyUserDetailsServiceImpl;
import top.haidong556.ac.service.AcService;
import top.haidong556.ac.service.BillService;
import top.haidong556.ac.service.ScheduleService;
import top.haidong556.ac.util.GlobalConfig;

@Controller
@RequestMapping("/user")
public class UserController {

    private AcService acService;
    private ScheduleService scheduleService;
    private MyUserDetailsServiceImpl userDetailsService;
    private BillService billService;

    @Autowired
    public UserController(ScheduleService scheduleService,MyUserDetailsServiceImpl userDetailsService,AcService acService,BillService billService){
        this.userDetailsService=userDetailsService;
        this.scheduleService=scheduleService;
        this.billService=billService;
        this.acService=acService;
    }
    @GetMapping()
    public ModelAndView getUserPage()throws Exception{
        User user = userDetailsService.currentUser();
        int acId = user.getAcId();
        Ac acState = acService.getAcState(acId);
        ModelAndView modelAndView=new ModelAndView("user");
        float cost = billService.getCost(user.getUserId());
        modelAndView.addObject("cost",cost);
        modelAndView.addObject("acState",acState);
        modelAndView.addObject("user",user);
        System.out.println(user);
        return modelAndView;
    }
    @GetMapping("/temp")
    public ModelAndView changeAcTemp(int newTemp)throws Exception{
        User user = userDetailsService.currentUser();
        int acId = user.getAcId();
        scheduleService.changeAcTemp(acId,newTemp,userDetailsService.currentUser().getUserId());
        return getModelAndView(user, acId);
    }

    @GetMapping("/windSpeed")
    public ModelAndView changeAcWindSpeed(@RangeValidation(max="AC_MAX_WIND_SPEED",min = "AC_MIN_WIND_SPEED",msg = "controller层：修改风速的参数异常") int newWindSpeed)throws Exception{
        int windSpeed=Integer.valueOf(newWindSpeed);
        User user = userDetailsService.currentUser();
        int acId = user.getAcId();
        scheduleService.changeAcWindSpeed(acId,windSpeed,userDetailsService.currentUser().getUserId());
        return getModelAndView(user, acId);
    }
    @GetMapping("/close")
    public ModelAndView close()throws Exception{
        User user = userDetailsService.currentUser();
        int acId = user.getAcId();
        scheduleService.closeAc(acId,userDetailsService.currentUser().getUserId());
        return getModelAndView(user, acId);
    }
    @GetMapping("/open")
    public ModelAndView open()throws Exception{
        User user = userDetailsService.currentUser();
        int acId = user.getAcId();
        scheduleService.openAc(acId, GlobalConfig.AC_DEFAULT_WIND_SPEED,userDetailsService.currentUser().getUserId());//
        return getModelAndView(user, acId);
    }

    private ModelAndView getModelAndView(User user, int acId) throws Exception {
        Ac acState = acService.getAcState(acId);
        ModelAndView modelAndView=new ModelAndView("user");
        float cost = billService.getCost(user.getUserId());
        modelAndView.addObject("cost",cost);
        modelAndView.addObject("acState",acState);
        modelAndView.addObject("user",user);
        return modelAndView;
    }


}
