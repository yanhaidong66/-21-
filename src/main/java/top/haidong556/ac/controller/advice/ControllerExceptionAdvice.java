package top.haidong556.ac.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.haidong556.ac.exception.BaseException;

@ControllerAdvice
public class ControllerExceptionAdvice {
    public static final Logger errorLogger= LoggerFactory.getLogger("errorLogger");
    @ExceptionHandler(BaseException.class)
    public ModelAndView exceptionHandler(BaseException ex){
        errorLogger.warn(ex.getMsg());
        ModelAndView modelAndView=new ModelAndView("exceptionPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.addObject("stackTrace", ex.getStackTrace().toString());
        return modelAndView;
    }
}
