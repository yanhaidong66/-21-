package top.haidong556.ac.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.util.RandomData;

import java.util.UUID;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AcServiceTest {
    @Autowired
    AcService acService;
    @Autowired
    UserService userService;
    Ac ac;
    User user;
    @BeforeEach
    void initTestData() throws Exception {
        ac=RandomData.getRandomAc();
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
    void getAcState() throws Exception {
        System.out.println(acService.getAcState(ac.getAcId()));
    }

    @Test
    void addAc() {
        System.out.println(ac);
    }

    @Test
    void changeAcTemp() throws Exception {
        acService.changeAcTemp(ac.getAcId(),ac.getTemp()+10,user.getUserId());
        Ac acState = acService.getAcState(ac.getAcId());
        assertEquals(ac.getTemp()+10,acState.getTemp());
    }

    @Test
    void changeAcWindSpeed() throws Exception {
        acService.changeAcWindSpeed(ac.getAcId(), ac.getWindSpeed()+1, user.getUserId());
        Ac acState = acService.getAcState(ac.getAcId());
        assertEquals(ac.getWindSpeed()+1, acState.getWindSpeed());
    }

    @Test
    void closeAc() throws Exception {
        acService.closeAc(ac.getAcId(),user.getUserId());
        Ac acState = acService.getAcState(ac.getAcId());
        assertEquals(acState.getAcState(), Ac.AcState.CLOSE);
    }

    @Test
    void openAc() throws Exception {
        acService.openAc(ac.getAcId(),user.getUserId());
        Ac acState = acService.getAcState(ac.getAcId());
        assertEquals(acState.getAcState(), Ac.AcState.OPEN);
    }

    @Test
    void getAcDetailTableByUserId() {
    }

    @Test
    void getAcDetailTableByAcId() {
    }


    @Test
    void getAllAcState() {
    }

    @Test
    void createOperationItem() {
    }

    @Test
    void deleteAc() {
    }

    @Test
    void getRoomTemp() {
    }

    @Test
    void changeRoomTemp() {
    }
}