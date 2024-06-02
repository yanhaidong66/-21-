package top.haidong556.ac.entity.ac;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import top.haidong556.ac.util.GlobalConfig;


@Data
public class Ac {
    private AcState acState=AcState.CLOSE;
    private int acId;
    private int windSpeed= GlobalConfig.AC_DEFAULT_WIND_SPEED;
    private int temp=GlobalConfig.AC_DEFAULT_TEMP;
    private String room;
    private float roomTemp=GlobalConfig.ROOM_DEFAULT_TEMP;

    public enum AcState {
         CLOSE,OPEN;
    }
    public void setWindSpeed(int windSpeed){
        if(windSpeed>3)
            this.windSpeed=3;
        else if(windSpeed<1)
            this.windSpeed=1;
        else if(windSpeed>=1&&windSpeed<=3)
            this.windSpeed=windSpeed;
    }

}
