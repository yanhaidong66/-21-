package top.haidong556.ac.entity.ac;

import lombok.Data;
import org.springframework.stereotype.Component;
import top.haidong556.ac.annotations.RangeValidation;
import top.haidong556.ac.util.GlobalConfig;


@Data
@Component
public class Ac {
    private AcState acState=AcState.CLOSE;
    private int acId;
    private AcWindSpeed windSpeed= setWindSpeed(GlobalConfig.AC_DEFAULT_WIND_SPEED);
    private final int AC_MAX_TEMP = GlobalConfig.AC_MAX_TEMP;
    private final int AC_MIN_TEMP = GlobalConfig.AC_MIN_TEMP;
    @RangeValidation(max = "AC_MAX_TEMP" ,min = "AC_MIN_TEMP",msg = "aop异常：ac类的温度设置超出范围")
    private int temp=GlobalConfig.AC_DEFAULT_TEMP;
    private String room;
    private float roomTemp=GlobalConfig.ROOM_DEFAULT_TEMP;

    public enum AcState {
         CLOSE,OPEN;
    }
    public enum AcWindSpeed{
        EMPTY,LOW,MID,HIGH;
    }
    public AcWindSpeed setWindSpeed(int windSpeed){
        if(windSpeed>=3)
            this.windSpeed=AcWindSpeed.HIGH;
        else if(windSpeed<=1)
            this.windSpeed=AcWindSpeed.LOW;
        else
            this.windSpeed=AcWindSpeed.MID;
        return this.windSpeed;
    }
    public void setWindSpeed(AcWindSpeed acWindSpeed){
        this.windSpeed=windSpeed;
    }
    public int getWindSpeed(){
        return this.windSpeed.ordinal();
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
