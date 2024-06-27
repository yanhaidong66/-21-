package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.bill.BillItem;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.entity.role.Waiter;
import top.haidong556.ac.security.MyUserDetailsServiceImpl;
import top.haidong556.ac.service.AcService;
import top.haidong556.ac.service.BillService;
import top.haidong556.ac.service.UserService;
import top.haidong556.ac.util.GlobalConfig;

import java.time.LocalDateTime;
import java.util.LinkedList;
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
    public ModelAndView checkin(@RequestParam String username,@RequestParam String password,@RequestParam String room){
        try {
            User user = new User(username, password);
            Ac ac = acService.getAcState(room);
            if(ac==null){
                Ac newAc=new Ac();
                newAc.setRoom(room);
                newAc.setTemp(GlobalConfig.AC_DEFAULT_TEMP);
                newAc.setWindSpeed(GlobalConfig.AC_DEFAULT_WIND_SPEED);
                newAc.setAcState(Ac.AcState.CLOSE);
                newAc.setRoomTemp(GlobalConfig.ROOM_DEFAULT_TEMP);
                acService.addAc(newAc);
                ac=newAc;
            }
            user.setAcId(ac.getAcId());
            userService.createUser(user);

            ModelAndView modelAndView = new ModelAndView("waiter-checkin");
            modelAndView.addObject("error",false);
            Waiter waiter = userDetailsService.currentWaiter();
            modelAndView.addObject("waiter", waiter);
            return modelAndView;
        }catch (Exception e){
            ModelAndView modelAndView = new ModelAndView("waiter-checkin");
            Waiter waiter = userDetailsService.currentWaiter();
            modelAndView.addObject("waiter", waiter);
            modelAndView.addObject("error",true);
            return modelAndView;
        }
    }
    @GetMapping("/checkout")
    public ModelAndView checkoutPage()throws Exception{
        ModelAndView modelAndView=new ModelAndView("waiter-checkout");
        float cost=0f;
        List<OperationItem> acDetailTableByUserId = new LinkedList<>();
        Waiter waiter = userDetailsService.currentWaiter();
        modelAndView.addObject("cost","init");
        modelAndView.addObject("waiter",waiter);
        modelAndView.addObject("opDetails",acDetailTableByUserId);
        return modelAndView;
    }
    @PostMapping("/checkout")
    public ModelAndView checkout(String username)throws Exception{
        ModelAndView modelAndView=new ModelAndView("waiter-checkout");
        User user = userService.getUser(username);
        List<OperationItem> acDetailTableByUserId = acService.getAcDetailTableByUserId(user.getUserId());
        Waiter waiter = userDetailsService.currentWaiter();
        acService.closeAc(user.getAcId(), user.getUserId());
        float cost= userService.deleteUser(user.getUserId());
        acService.deleteAc()
        modelAndView.addObject("cost",cost);
        modelAndView.addObject("waiter",waiter);
        modelAndView.addObject("opDetails",acDetailTableByUserId);
        return modelAndView;
    }
    @GetMapping("/allUser")
    public ModelAndView allUserPage()throws Exception{
        ModelAndView modelAndView=new ModelAndView("waiter-allUser");
        Waiter waiter = userDetailsService.currentWaiter();
        modelAndView.addObject("waiter",waiter);
        List<User> allUser = userService.getAllUser();
        modelAndView.addObject("allUser",allUser);
        return modelAndView;
    }
}
