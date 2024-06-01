package top.haidong556.ac.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AcServiceTest {
    @Autowired
    AcService acService;

    @Test
    void getAcState() {
        while(true){
            Ac acState = acService.getAcState(1);
            System.out.println(acState);
        }
    }
}