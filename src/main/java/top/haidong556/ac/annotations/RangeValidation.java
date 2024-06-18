package top.haidong556.ac.annotations;

import top.haidong556.ac.util.GlobalConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface RangeValidation {
    String max() default "DEFAULT_MAX";
    String min() default "DEFAULT_MIN";
    String msg() default "设置值的超出范围";
}
