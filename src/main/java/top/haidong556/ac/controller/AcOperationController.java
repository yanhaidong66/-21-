package top.haidong556.ac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AcOperationController {
    @GetMapping("/getAcOperationTable/{userId}")
    public void getAcOperationTableByUserId(@PathVariable("userId") int userId){

    }

}
