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
import top.haidong556.ac.util.RandomData;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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

    private List<Ac> acs=new LinkedList<>();
    private List<User> users=new LinkedList<>();
    @BeforeEach
    void setupTestData() throws Exception {
        ac= RandomData.getRandomAc();
        acService.addAc(ac);
        user=RandomData.getRandomUser(ac.getAcId());
        userService.createUser(user);
        thread=new Thread(scheduleService);
        thread.start();
    }
    @AfterEach
    void deleteTestData() throws Exception {
        userService.deleteUser(user.getUserId());
        acService.deleteAc(ac.getAcId());
        thread.join(1000);
    }

    @Test
    void start() throws Exception {
        System.out.println("SCHEDULE_TEST_STARE------------------------------------------------------------");
        for(int i=0;i<10;i++){
            Ac acTemp=RandomData.getRandomAc();
            acs.add(acTemp);
            acService.addAc(acTemp);
            User userTemp=RandomData.getRandomUser(acTemp.getAcId());
            userService.createUser(userTemp);
        }
        for(int i=0;i<10;i++){
            Ac ac1 = acs.get(i);
            System.out.println("Open ac "+ac1.getAcId());
            scheduleService.openAc(ac1.getAcId(),ac1.getWindSpeed(),GlobalConfig.SYSTEM_ID);
            int randomInt = RandomData.getRandomInt(0, i);
            Ac ac2 = acs.get(randomInt);
            int randomSpeed=RandomData.getRandomInt(0,4);
            ac2.setWindSpeed(randomSpeed);
            System.out.println("Change ac "+ac2.getAcId()+" new speed "+ac2.getWindSpeed());
            scheduleService.changeAcWindSpeed(ac2.getAcId(), ac2.getWindSpeed(), GlobalConfig.SYSTEM_ID);
        }
        System.out.println("SCHEDULE_TEST_END------------------------------------------------------------");

    }


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