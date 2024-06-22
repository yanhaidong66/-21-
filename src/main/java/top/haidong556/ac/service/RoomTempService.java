package top.haidong556.ac.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger warnLogger= LoggerFactory.getLogger("warnFile");


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
                        continue;
                } catch (DataBaseException e) {
                    warnLogger.warn("RoomTempThread:通过acId获取user失败");
                    continue;
                }

                float roomTemp = ac.getRoomTemp();
                int acTemp = ac.getTemp();
                int windSpeed = ac.getWindSpeed();
                float changeRate=GlobalConfig.ROOM_TEMP_CHANGE_RATE;


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
                                scheduleService.closeAc(ac.getAcId(),userService.getUserByAcId(ac.getAcId()).get(0).getUserId());
                                log.info("roomTempThread:关闭空调:"+ac.getAcId());
                            } catch (Exception e) {
                                warnLogger.warn("roomTempThread:关闭空调失败:"+ac.getAcId());
                            }
                        }
                    }
                    else if(roomTemp<acTemp){
                        roomTemp=roomTemp+changeRate;
                        if(roomTemp>=acTemp){
                            roomTemp=acTemp;
                            try {
                                scheduleService.closeAc(ac.getAcId(),userService.getUserByAcId(ac.getAcId()).get(0).getUserId());
                                log.info("roomTempThread:关闭空调:"+ac.getAcId());
                            } catch (Exception e) {
                                warnLogger.warn("roomTempThread:关闭空调失败:"+ac.getAcId());
                            }
                        }
                    }else{
                        try {
                            scheduleService.closeAc(ac.getAcId(),userService.getUserByAcId(ac.getAcId()).get(0).getUserId());
                            log.info("roomTempThread:关闭空调"+ac.getAcId());
                        } catch (Exception e) {
                            warnLogger.warn("roomTempThread:关闭空调失败:"+ac.getAcId());
                        }
                    }
                    try {
                        acService.changeRoomTemp(ac.getAcId(),roomTemp);
                        log.info("roomTempThread:空调"+ac.getAcId()+"修改室温"+roomTemp);
                    } catch (Exception e) {
                        warnLogger.warn("roomTempThread:修改室温失败:"+ac.getAcId());
                    }
                }else if(ac.getAcState()== Ac.AcState.CLOSE) {
                    if(roomTemp>DEFAULT_ROOM_TEMP){
                        roomTemp-=GlobalConfig.ROOM_TEMP_CHANGE_RATE;
                    }else {
                        roomTemp+=GlobalConfig.ROOM_TEMP_CHANGE_RATE;
                    }
                    try {
                        acService.changeRoomTemp(ac.getAcId(),roomTemp);
                        log.info("roomTempThread:空调"+ac.getAcId()+"修改室温为"+roomTemp);

                    } catch (Exception e) {
                        warnLogger.warn("roomTempThread:修改室温失败:"+ac.getAcId());

                    }
                    if(Math.abs(ac.getTemp()-roomTemp)>=1){
                        try {
                            scheduleService.openAc(ac.getAcId(),GlobalConfig.AC_DEFAULT_WIND_SPEED,userService.getUserByAcId(ac.getAcId()).get(0).getUserId());
                            log.info("roomTempThread:开启空调:"+ac.getAcId());
                        } catch (Exception e) {
                            warnLogger.warn("roomTempThread:关闭空调失败:"+ac.getAcId());
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
