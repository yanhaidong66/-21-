package top.haidong556.ac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.service.AcService;
import top.haidong556.ac.util.GlobalConfig;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AcService acService;
    public AdminController(AcService acService){
        this.acService=acService;
    }


    @GetMapping()
    public ModelAndView getAllAcState()throws Exception{
        ModelAndView modelAndView=new ModelAndView("admin");
        List<Ac> allAcState = acService.getAllAcState();
        modelAndView.addObject("allAc",allAcState);
        return modelAndView;
    }

    @GetMapping("/addAc")
    public ModelAndView addAc(String room)throws Exception{
        Ac ac=new Ac();
        ac.setRoom(room);
        acService.addAc(ac);
        ModelAndView modelAndView=new ModelAndView("admin");
        List<Ac> allAcState = acService.getAllAcState();
        modelAndView.addObject("allAc",allAcState);
        return modelAndView;
    }
    @GetMapping("/temp")
    public ModelAndView changeAcTemp(int acId, int newTemp)throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        acService.changeAcTemp(acId,newTemp, GlobalConfig.ADMIN_ID);
        return modelAndView;
    }

    @GetMapping("/windSpeed")
    public ModelAndView changeAcWindSpeed(int acId,int newWindSpeed)throws Exception{
        acService.changeAcWindSpeed(acId,newWindSpeed, GlobalConfig.ADMIN_ID);
        ModelAndView modelAndView=new ModelAndView("admin");
        List<Ac> allAcState = acService.getAllAcState();
        modelAndView.addObject("allAc",allAcState);
        return modelAndView;
    }
    @GetMapping("/close")
    public ModelAndView closeAc(int acId)throws Exception{
        acService.closeAc(acId, GlobalConfig.ADMIN_ID);
        ModelAndView modelAndView=new ModelAndView("admin");
        List<Ac> allAcState = acService.getAllAcState();
        modelAndView.addObject("allAc",allAcState);
        return modelAndView;
    }
    @GetMapping("/open")
    public ModelAndView openAc(int acId)throws Exception{
        acService.openAc(acId, GlobalConfig.ADMIN_ID);
        ModelAndView modelAndView=new ModelAndView("admin");
        List<Ac> allAcState = acService.getAllAcState();
        modelAndView.addObject("allAc",allAcState);
        return modelAndView;
    }

}
