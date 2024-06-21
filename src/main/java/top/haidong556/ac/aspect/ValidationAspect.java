package top.haidong556.ac.aspect;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import top.haidong556.ac.annotations.RangeValidation;
import top.haidong556.ac.exception.ArgsException;
import top.haidong556.ac.util.GlobalConfig;
import top.haidong556.ac.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Component
@Log4j2
public class ValidationAspect {
    @Before("execution(* top.haidong556.ac.entity.*.*.set*(..)) && args(newValue)")
    public void validateSetFiledArguments(JoinPoint joinPoint, Object newValue) throws IllegalAccessException {

        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        String fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);

        Field field = null;
        try {
            field = target.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException ignored) {
            return;
        }
        if (field.isAnnotationPresent(RangeValidation.class)) {
            RangeValidation rangeValidation = field.getAnnotation(RangeValidation.class);
            Class<GlobalConfig> globalConfigClass = GlobalConfig.class;
            int min;
            int max;
            try {
                Field maxF = globalConfigClass.getDeclaredField(rangeValidation.max());
                maxF.setAccessible(true);
                Field minF = globalConfigClass.getDeclaredField(rangeValidation.min());
                minF.setAccessible(true);
                max = maxF.getInt(null);
                min=minF.getInt(null);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            String msg = rangeValidation.msg();

                int value = (int) newValue;
                if(ValidationUtil.validateRange(value,min,max)==false){
                    throw new IllegalAccessException(msg);
                }
            }

    }


    @Before("execution(* top.haidong556.ac.*.*.* (..))")
    public void validateParam(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i=0;i< parameterAnnotations.length;i++) {
            for (Annotation anno :
                    parameterAnnotations[i]) {
                if (anno instanceof RangeValidation) {
                    RangeValidation rangeValidation = (RangeValidation) anno;
                    Class<GlobalConfig> globalConfigClass = GlobalConfig.class;
                    int min;
                    int max;
                    try {
                        Field maxF = globalConfigClass.getDeclaredField(rangeValidation.max());
                        maxF.setAccessible(true);
                        Field minF = globalConfigClass.getDeclaredField(rangeValidation.min());
                        minF.setAccessible(true);
                        max = maxF.getInt(null);
                        min=minF.getInt(null);
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    String msg = ((RangeValidation) anno).msg();
                    if(ValidationUtil.validateRange((int)args[i], min, max)==false){
                        log.error(msg);
                        throw new ArgsException(msg);
                    }
                }

            }
        }
    }



}
