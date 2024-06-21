package top.haidong556.ac;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import top.haidong556.ac.service.RoomTempService;

@SpringBootApplication
@EnableWebSecurity
@EnableAspectJAutoProxy
@Slf4j
public class TemperatureControllerApplication implements CommandLineRunner {

    private RoomTempService roomTempService;
    public TemperatureControllerApplication(RoomTempService roomTempService){
        this.roomTempService=roomTempService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TemperatureControllerApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        log.info("room Temp system run");
        Thread roomTempServiceThread = new Thread(roomTempService);
        roomTempServiceThread.start();
    }
}
