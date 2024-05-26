package top.haidong556.ac.controller;

import top.haidong556.ac.service.AcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AcController {
    private AcService acService;
    @Autowired
    private void setAcService(){}
    @PostMapping("/addAc")
    public void addAc(int acId){

    }
    @PatchMapping("/changeAcTemp")
    public void changeAcTemp(int acId){

    }

    @PatchMapping("/changeAcWindSpeed")
    public void changeAcWindSpeed(int acId){

    }
    @GetMapping()
    public void closeAc(int acId){

    }
    @GetMapping()
    public void openAc(int acId){

    }
    @GetMapping()
    public void getCost(int acId){

    }
    @GetMapping("/getAcState")
    public void getAcState(int acId){

    }
    public void getAcOperationDetail(int acId){

    }

}
