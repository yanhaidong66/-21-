package top.haidong556.ac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.service.AcService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AcService acService;
    public AdminController(AcService acService){
        this.acService=acService;
    }

    @GetMapping("/all")
    public String getAllAcState(){
        List<Ac> allAcState = acService.getAllAcState();
        return null;
    }
    @PostMapping()
    public void addAc(Ac newAc){
        acService.addAc(newAc);
    }
    @PatchMapping("/temp")
    public ModelAndView changeAcTemp(int acId, int newTemp){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
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
    @GetMapping()
    public ModelAndView getAcState(){
        Ac acState = acService.getAcState();
        ModelAndView modelAndView=new ModelAndView("ac");
        modelAndView.addObject("acState",acState);
        return modelAndView;
    }
}
