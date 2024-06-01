package top.haidong556.ac.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.mapper.AcMapper;
import top.haidong556.ac.repository.AcRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoomTempServiceTest {
    @Autowired
    RoomTempService roomTempService;
    @Test
    void run() throws InterruptedException {
        Thread thread=new Thread(roomTempService);
        thread.start();
        thread.join();
        System.out.println("end");
    }
}