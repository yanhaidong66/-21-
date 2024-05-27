package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.entity.operationDetail.OperationTable;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.service.AcService;
import top.haidong556.ac.service.UserService;

@Controller
@RequestMapping("waiter")
public class WaiterController {
    private UserService userService;
    private AcService acService;
    @Autowired
    public WaiterController(UserService userService,AcService acService){
        this.userService=userService;
        this.acService=acService;
    }

    @PostMapping("/addUser")
    public ModelAndView addUserPage(){
        ModelAndView modelAndView = new ModelAndView("addUser");
        return modelAndView;
    }


    @PostMapping("/checkout")
    public ModelAndView checkout(int userId){
        ModelAndView modelAndView=new ModelAndView("checkout");
        OperationTable acDetailTableByUserId = acService.getAcDetailTableByUserId(userId);

        userService.deleteUser(userId);
        return modelAndView;

    }
    @GetMapping("/")
    public ModelAndView getWaiterPage(){
        ModelAndView modelAndView=new ModelAndView("waiter");
        return modelAndView;
    }
}
