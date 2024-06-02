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

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("waiter")
public class WaiterController {
    private UserService userService;
    private AcService acService;
    private MyUserDetailsServiceImpl userDetailsService;
    private BillService billService;

    @Autowired
    public WaiterController(UserService userService,AcService acService,MyUserDetailsServiceImpl userDetailsService,BillService billService){
        this.userService=userService;
        this.acService=acService;
        this.userDetailsService=userDetailsService;
        this.billService=billService;
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
            user.setAcId(ac.getAcId());
            userService.createUser(user);
            System.out.println(user);
            BillItem billItem = new BillItem.Builder()
                    .setAcId(ac.getAcId())
                    .setCreateTime(LocalDateTime.now())
                    .setUserId(user.getUserId())
                    .setState(BillItem.BillState.NOT_PAY)
                    .setCost(0)
                    .build();
            billService.createBillItem(billItem);
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
    public ModelAndView checkoutPage(String username)throws Exception{
        ModelAndView modelAndView=new ModelAndView("waiter-checkout");
        User user = userService.getUser(username);
        Ac ac=acService.getAcState(user.getAcId());
        float cost = billService.getCost(user.getUserId());
        BillItem billItem = new BillItem.Builder()
                .setAcId(ac.getAcId())
                .setCreateTime(LocalDateTime.now())
                .setUserId(user.getUserId())
                .setState(BillItem.BillState.HAVE_PAY)
                .setCost(cost)
                .build();
        billService.createBillItem(billItem);
        List<OperationItem> acDetailTableByUserId = acService.getAcDetailTableByUserId(user.getUserId());
        Waiter waiter = userDetailsService.currentWaiter();
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
