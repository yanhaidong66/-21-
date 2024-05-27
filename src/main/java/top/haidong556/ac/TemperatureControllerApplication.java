package top.haidong556.ac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class TemperatureControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemperatureControllerApplication.class, args);
    }
}
