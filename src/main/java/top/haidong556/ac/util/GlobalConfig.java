package top.haidong556.ac.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalConfig {
    static {
        InputStream config = GlobalConfig.class.getClassLoader().getResourceAsStream("global_config.properties");
        Properties property=new Properties();
        try {
            property.load(config);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ROOM_DEFAULT_TEMP = Integer.parseInt(property.getProperty("ROOM_DEFAULT_TEMP"),25);
        PER_SECOND_MILLISECOND = Integer.parseInt(property.getProperty("PER_SECOND_MILLISECOND"),1000);  // 固定值，无需从 properties 文件读取
        AC_DEFAULT_WIND_SPEED = Integer.parseInt(property.getProperty("AC_DEFAULT_WIND_SPEED"),2);
        AC_DEFAULT_TEMP = Integer.parseInt(property.getProperty("AC_DEFAULT_TEMP"),15);
        ADMIN_ID = Integer.parseInt(property.getProperty("ADMIN_ID"),3);
        WAITER_ID = Integer.parseInt(property.getProperty("WAITER_ID"),2);
        MANAGER_ID = Integer.parseInt(property.getProperty("MANAGER_ID"),4);
        SYSTEM_ID = Integer.parseInt(property.getProperty("SYSTEM_ID"),1);
        AC_COST_PER_SECOND = Float.parseFloat(property.getProperty("AC_COST_PER_SECOND","0.1"));
        INIT_AC = Boolean.parseBoolean(property.getProperty("INIT_AC","false"));
        SHOW_REPOSITORY_OPERATION_MESSAGE = Boolean.parseBoolean(property.getProperty("SHOW_REPOSITORY_OPERATION_MESSAGE","false"));
    }
    public static final int ROOM_DEFAULT_TEMP;
    public static final int PER_SECOND_MILLISECOND;
    public static final int AC_DEFAULT_WIND_SPEED;
    public static final int AC_DEFAULT_TEMP;
    public static final int ADMIN_ID;
    public static final int WAITER_ID;
    public static final int MANAGER_ID;
    public static final int SYSTEM_ID;
    public static final float AC_COST_PER_SECOND;
    public static final boolean INIT_AC;
    public static final boolean SHOW_REPOSITORY_OPERATION_MESSAGE;
}
