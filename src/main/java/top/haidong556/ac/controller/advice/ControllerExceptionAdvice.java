package top.haidong556.ac.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.exception.BaseException;

@ControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(BaseException.class)
    public ModelAndView exceptionHandler(BaseException ex){
        ModelAndView modelAndView=new ModelAndView("exceptionPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.addObject("stackTrace", ex.getStackTrace().toString());
        return modelAndView;
    }
}
