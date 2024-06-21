package top.haidong556.ac.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.exception.DataBaseException;
import top.haidong556.ac.util.GlobalConfig;

import java.util.List;

import static java.lang.Thread.sleep;

@Service
@Slf4j
public class RoomTempService implements Runnable{
    private  int DEFAULT_ROOM_TEMP=GlobalConfig.ROOM_DEFAULT_TEMP;
    private AcService acService;
    private UserService userService;
    private ScheduleService scheduleService;
    @Autowired
    public RoomTempService(AcService acService,ScheduleService scheduleService,UserService userService){
        this.scheduleService=scheduleService;
        this.acService=acService;
        this.userService=userService;
    }

    @Override
    public void run() {
        while(true) {
            List<Ac> allAcState = null;
            try {
                allAcState = acService.getAllAcState();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (Ac ac : allAcState) {
                try {
                    List<User> userByAcId = userService.getUserByAcId(ac.getAcId());
                    if (userByAcId.isEmpty())
                        break;
                } catch (DataBaseException e) {
                    log.warn("RoomTempThread:通过acId获取user失败");
                    break;
                }
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
                            try {
                                scheduleService.closeAc(ac.getAcId(),GlobalConfig.SYSTEM_ID);
                                log.info("roomTempThread:关闭空调"+ac.getAcId());
                            } catch (Exception e) {
                                log.warn("roomTempThread:关闭空调失败"+ac.getAcId());
                            }
                        }
                    }
                    else if(roomTemp<acTemp){
                        roomTemp=roomTemp+changeRate;
                        if(roomTemp>=acTemp){
                            roomTemp=acTemp;
                            try {
                                scheduleService.closeAc(ac.getAcId(),GlobalConfig.SYSTEM_ID);
                                log.info("roomTempThread:关闭空调"+ac.getAcId());

                            } catch (Exception e) {
                                log.warn("roomTempThread:关闭空调失败"+ac.getAcId());

                            }
                        }
                    }else{
                        try {
                            scheduleService.closeAc(ac.getAcId(),GlobalConfig.SYSTEM_ID);
                            log.info("roomTempThread:关闭空调"+ac.getAcId());

                        } catch (Exception e) {
                            log.warn("roomTempThread:关闭空调失败"+ac.getAcId());

                        }
                    }
                    try {
                        acService.changeRoomTemp(ac.getAcId(),roomTemp);
                        log.info("roomTempThread:修改室温"+ac.getAcId());
                    } catch (Exception e) {
                        log.warn("roomTempThread:修改室温失败"+ac.getAcId());

                    }
                }else {
                    if(roomTemp>DEFAULT_ROOM_TEMP){
                        roomTemp-=0.5f;
                    }else {
                        roomTemp+=0.5f;
                    }
                    try {
                        acService.changeRoomTemp(ac.getAcId(),roomTemp);
                        log.info("roomTempThread:修改室温"+ac.getAcId());

                    } catch (Exception e) {
                        log.warn("roomTempThread:修改室温失败"+ac.getAcId());

                    }
                    if(Math.abs(ac.getTemp()-roomTemp)>=1){
                        try {
                            scheduleService.openAc(ac.getAcId(),GlobalConfig.AC_DEFAULT_WIND_SPEED,GlobalConfig.SYSTEM_ID);
                            log.info("roomTempThread:开启空调"+ac.getAcId());

                        } catch (Exception e) {
                            log.warn("roomTempThread:关闭空调失败"+ac.getAcId());

                        }
                    }
                }

            }

            try {
                sleep(GlobalConfig.PER_SECOND_MILLISECOND * 60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
