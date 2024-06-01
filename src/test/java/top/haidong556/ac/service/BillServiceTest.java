package top.haidong556.ac.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.util.RandomData;

import java.time.LocalDateTime;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BillServiceTest {
    @Autowired
    BillService billService;
    @Autowired
    AcService acService;
    @Autowired
    UserService userService;
    Ac ac;
    User user;
    @BeforeEach
    void initTestData() throws Exception {
        ac= RandomData.getRandomAc();
        acService.addAc(ac);
        user=RandomData.getRandomUser(ac.getAcId());
        userService.createUser(user);
    }
    @AfterEach
    void deleteTestData() throws Exception {
        userService.deleteUser(user.getUserId());
        acService.deleteAc(ac.getAcId());

    }

    @Test
    void getBillItemByTime() throws Exception {
        System.out.println(billService.getBillItemByTime(LocalDateTime.now().minusDays(3), LocalDateTime.now()));
    }

    @Test
    void getCost() throws Exception {
        acService.openAc(ac.getAcId(),user.getUserId());
        System.out.println("windSpeed:"+acService.getAcState(ac.getAcId()).getWindSpeed());
        sleep(10000);
        acService.changeAcWindSpeed(ac.getAcId(),2,user.getUserId());
        float cost = billService.getCost(user.getUserId());
        System.out.println(cost);
        sleep(10000);
        cost= billService.getCost(user.getUserId());
        System.out.println(cost);
        acService.changeAcWindSpeed(ac.getAcId(),3,user.getUserId());
        sleep(10000);

        cost= billService.getCost(user.getUserId());
        System.out.println(cost);
    }
}