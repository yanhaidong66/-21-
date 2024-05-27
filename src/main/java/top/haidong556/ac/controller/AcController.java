package top.haidong556.ac.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.service.AcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

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
    public ModelAndView changeAcTemp(int acId, int newTemp){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("home");
        acService.changeAcTemp(acId,newTemp);
        return modelAndView;
    }

    @PatchMapping("/windSpeed")
    public void changeAcWindSpeed(int acId,int newWindSpeed){
        acService.changeAcWindSpeed(acId,newWindSpeed);
    }
    @GetMapping("/close")
    public void closeAc(int acId){
        acService.closeAc(acId);
    }
    @GetMapping("/open")
    public void openAc(int acId){
        acService.openAc(acId);
    }
    @GetMapping("/state")
    public Ac getAcState(int acId){
        return acService.getAcState(acId);
    }
    @GetMapping("/all")
    public String getAllAcState(){
        List<Ac> allAcState = acService.getAllAcState();
        return null;
    }


}
