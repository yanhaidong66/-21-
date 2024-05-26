package top.haidong556.ac.entity.ac;

import lombok.Data;

@Data
public class Ac {
    private AcState acState;
    private int acId;
    private int windSpeed;
    private int temp;
    private String room;
    private int cost_per_hour;
    public enum AcState {
        OPEN,CLOSE;
    }
}
