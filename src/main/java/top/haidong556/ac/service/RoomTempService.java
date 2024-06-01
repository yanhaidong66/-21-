package top.haidong556.ac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.util.GlobalConfig;

import java.util.List;

import static java.lang.Thread.sleep;

@Service
public class RoomTempService implements Runnable{
    private final int perSecond= GlobalConfig.PER_SECOND_MILLISECOND;
    private final int DEFAULT_ROOM_TEMP=GlobalConfig.ROOM_DEFAULT_TEMP;
    private AcService acService;
    @Autowired
    public RoomTempService(AcService acService){
        this.acService=acService;
    }

    @Override
    public void run() {
        System.out.println("roomTempService run");
        while(true) {
            List<Ac> allAcState = acService.getAllAcState();
            for (Ac ac : allAcState) {
                float roomTemp = ac.getRoomTemp();
                int acTemp = ac.getTemp();
                int windSpeed = ac.getWindSpeed();
                float changeRate=0.5f;
                if(windSpeed==1){
                    changeRate*=0.8;
                }else if(windSpeed==3){
                    changeRate*=1.2;
                }
                if(ac.getAcState()== Ac.AcState.OPEN){
                    if(roomTemp>acTemp){
                        roomTemp=roomTemp-changeRate;
                        if(roomTemp<=acTemp){
                            roomTemp=acTemp;
                            acService.closeAc(ac.getAcId(),GlobalConfig.SYSTEM_ID);
                        }
                    }
                    else if(roomTemp<acTemp){
                        roomTemp=roomTemp+changeRate;
                        if(roomTemp>=acTemp){
                            roomTemp=acTemp;
                            acService.closeAc(ac.getAcId(),GlobalConfig.SYSTEM_ID);
                        }
                    }else{
                        acService.closeAc(ac.getAcId(),GlobalConfig.SYSTEM_ID);
                    }
                    acService.changeRoomTemp(ac.getAcId(),roomTemp);
                }else {
                    if(roomTemp>DEFAULT_ROOM_TEMP){
                        roomTemp-=0.5f;
                    }else {
                        roomTemp+=0.5f;
                    }
                    acService.changeRoomTemp(ac.getAcId(),roomTemp);
                    if(Math.abs(ac.getTemp()-roomTemp)>=1){
                        acService.openAc(ac.getAcId(),GlobalConfig.SYSTEM_ID);
                    }
                }

            }

            try {
                sleep(perSecond * 60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
