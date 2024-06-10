package top.haidong556.ac.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.repository.AcRepository;
import top.haidong556.ac.repository.UserRepository;
import top.haidong556.ac.util.GlobalConfig;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ScheduleThreadTest {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    AcService acService;
    @Autowired
    UserService userService;
    Thread thread;
    private User user;
    private Ac ac;
    @BeforeEach
    void setupTestData() throws Exception {
        ac = new Ac();
        ac.setWindSpeed(2);
        ac.setTemp(24);
        ac.setRoom(UUID.randomUUID().toString().replace("-", "").substring(0, 4));
        ac.setAcState(Ac.AcState.CLOSE);
        acService.addAc(ac);
        user = new User(UUID.randomUUID().toString().replace("-", "").substring(0, 10), "password1", ac.getAcId());
        userService.createUser(user);
        thread=new Thread(scheduleService);
        //scheduleService.openAc(ac.getAcId(), user.getUserId());
        thread.start();
    }
    @AfterEach
    void deleteTestData() throws Exception {
        userService.deleteUser(user.getUserId());
        acService.deleteAc(ac.getAcId());
        thread.join(5000);
    }

    @Test
    void start() throws InterruptedException {}


    @Test
    void changeAcTemp() throws Exception {
        int newTemp=ac.getTemp()+1;
        scheduleService.changeAcTemp(ac.getAcId(),newTemp, GlobalConfig.SYSTEM_ID);
        Ac acState = acService.getAcState(ac.getAcId());
        assertEquals(acState.getTemp(),newTemp);
    }

    @Test
    void changeAcWindSpeed() throws Exception {
        int newWindSpeed=ac.getWindSpeed()+1;
        scheduleService.changeAcWindSpeed(ac.getAcId(), newWindSpeed, GlobalConfig.SYSTEM_ID);
        Ac acState = acService.getAcState(ac.getAcId());
        assertEquals(acState.getWindSpeed(),newWindSpeed);
    }

    @Test
    void closeAc() throws Exception {
        scheduleService.closeAc(ac.getAcId(), GlobalConfig.SYSTEM_ID);
        Ac acState = acService.getAcState(ac.getAcId());
        assertEquals(acState.getAcState(), Ac.AcState.CLOSE);

    }

    @Test
    void openAc() throws Exception {
        scheduleService.openAc(ac.getAcId(), GlobalConfig.SYSTEM_ID,ac.getWindSpeed());
        Ac acState = acService.getAcState(ac.getAcId());
        assertEquals(acState.getAcState(), Ac.AcState.OPEN);
    }
}