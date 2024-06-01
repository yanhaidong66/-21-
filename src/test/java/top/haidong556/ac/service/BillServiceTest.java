package top.haidong556.ac.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BillServiceTest {
    @Autowired
    BillService billService;

    @Test
    void getBillItemByTime() {
        System.out.println(billService.getBillItemByTime(LocalDateTime.now().minusDays(3), LocalDateTime.now()));
    }
}