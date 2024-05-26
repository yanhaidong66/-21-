package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import top.haidong556.ac.entity.bill.BillTable;
import top.haidong556.ac.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("bill")
public class BillController {
    private BillService billService;
    @Autowired
    public void setBillService(){}
    @GetMapping()
    public void getCostTableByTime(LocalDateTime startTime,LocalDateTime endTime){
        BillTable billItemByTime = billService.getBillItemByTime(startTime, endTime);
    }

}
