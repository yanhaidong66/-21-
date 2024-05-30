package top.haidong556.ac.entity.ac;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data
public class Ac {
    private AcState acState;
    private int acId;
    private int windSpeed;
    private int temp;
    private String room;
    private int costPerHour;
    @Value("${ac.defaultRoomTemp}")
    private int nowRoomTemp;

    public enum AcState {
         CLOSE,OPEN;
    }
}
