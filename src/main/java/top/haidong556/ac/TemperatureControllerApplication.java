package top.haidong556.ac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import top.haidong556.ac.service.RoomTempService;

@SpringBootApplication
@EnableWebSecurity
public class TemperatureControllerApplication implements CommandLineRunner {

    @Autowired
    private RoomTempService roomTempService;

    public static void main(String[] args) {
        SpringApplication.run(TemperatureControllerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Thread roomTempServiceThread = new Thread(roomTempService);
//        roomTempServiceThread.start();
    }
}
