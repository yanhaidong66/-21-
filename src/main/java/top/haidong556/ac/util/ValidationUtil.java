package top.haidong556.ac.util;

import java.lang.reflect.Field;

public class ValidationUtil {
    public static boolean validateRange(int value,int min,int max)  {
        return (value >= min && value <= max);
    }
}
