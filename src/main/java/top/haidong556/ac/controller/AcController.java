package top.haidong556.ac.controller;

import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.service.AcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ac")
public class AcController {
    private AcService acService;
    @Autowired
    private void setAcService(){}
    @PostMapping()
    public void addAc(Ac newAc){
        acService.addAc(newAc);
    }
    @PatchMapping("/temp")
    public void changeAcTemp(int acId,int newTemp){

    }

    @PatchMapping("/windSpeed")
    public void changeAcWindSpeed(int acId,int newWindSpeed){

    }
    @GetMapping("/close")
    public void closeAc(int acId){
        acService.closeAc(acId);
    }
    @GetMapping("/open")
    public void openAc(int acId){
        acService.openAc(acId);
    }
    @GetMapping("/detail")
    public void getDetail(int acId){

    }
    @GetMapping("/state")
    public Ac getAcState(int acId){
        return acService.getAcState(acId);
    }


}
