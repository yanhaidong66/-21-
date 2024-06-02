package top.haidong556.ac.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView modelAndView=new ModelAndView("exceptionPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.addObject("stackTrace", ex.getStackTrace().toString());
        return modelAndView;
    }
}
