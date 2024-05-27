package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.entity.bill.BillTable;
import top.haidong556.ac.entity.role.Manager;
import top.haidong556.ac.service.BillService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private BillService billService;
    @Autowired
    public ManagerController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping()
    public ModelAndView getCostTableByTime(LocalDateTime startTime, LocalDateTime endTime){
        ModelAndView modelAndView=new ModelAndView("bill");
        BillTable billItemByTime = billService.getBillItemByTime(startTime, endTime);
        return modelAndView;
    }
}
