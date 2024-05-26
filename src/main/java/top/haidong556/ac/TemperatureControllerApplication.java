package top.haidong556.ac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.haidong556.ac.mapper")
public class TemperatureControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemperatureControllerApplication.class, args);
    }
}
