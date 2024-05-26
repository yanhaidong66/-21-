package top.haidong556.ac.controller;

import top.haidong556.ac.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class BillController {
    private BillService billService;
    @GetMapping("/getCostTableByTime")
    public void getCostTableByTime(startTime,endTime){

    }
}
