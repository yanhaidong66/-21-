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

        ROOM_DEFAULT_TEMP=25;
        PER_SECOND_MILLISECOND=1000;
        AC_DEFAULT_WIND_SPEED=1;
        AC_DEFAULT_TEMP=20;
        ADMIN_ID =3;
        WAITER_ID=2;
        MANAGER_ID=4;
        SYSTEM_ID=5;
        AC_COST_PER_SECOND=0.1f;
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
}
