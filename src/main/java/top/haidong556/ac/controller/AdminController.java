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
    public ModelAndView getAllAcState(){
        ModelAndView modelAndView=new ModelAndView("allAc");
        List<Ac> allAcState = acService.getAllAcState();
        return modelAndView;
    }
    @PostMapping()
    public ModelAndView addAc(Ac newAc){
        acService.addAc(newAc);
        ModelAndView modelAndView=new ModelAndView("allAc");
        List<Ac> allAcState = acService.getAllAcState();
        modelAndView.addObject("allAc",allAcState);
        return modelAndView;
    }
    @PatchMapping("/temp")
    public ModelAndView changeAcTemp(int acId, int newTemp){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        acService.changeAcTemp(acId,newTemp);
        return modelAndView;
    }

    @PatchMapping("/windSpeed")
    public ModelAndView changeAcWindSpeed(int acId,int newWindSpeed){
        acService.changeAcWindSpeed(acId,newWindSpeed);
        ModelAndView modelAndView=new ModelAndView("allAc");
        List<Ac> allAcState = acService.getAllAcState();
        modelAndView.addObject("allAc",allAcState);
        return modelAndView;
    }
    @GetMapping("/close")
    public ModelAndView closeAc(int acId){
        acService.closeAc(acId);
        ModelAndView modelAndView=new ModelAndView("allAc");
        List<Ac> allAcState = acService.getAllAcState();
        modelAndView.addObject("allAc",allAcState);
        return modelAndView;
    }
    @GetMapping("/open")
    public ModelAndView openAc(int acId){
        acService.openAc(acId);
        ModelAndView modelAndView=new ModelAndView("allAc");
        List<Ac> allAcState = acService.getAllAcState();
        modelAndView.addObject("allAc",allAcState);
        return modelAndView;
    }

}
