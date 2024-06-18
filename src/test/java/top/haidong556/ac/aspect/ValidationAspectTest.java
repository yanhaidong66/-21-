package top.haidong556.ac.aspect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.service.AcService;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ValidationAspectTest {
    @Autowired
    AcService acService;
    @Autowired
    Ac ac;

    @Test
    void validateSetFiledArguments() {
        ac.setTemp(45);
    }

    @Test
    void validateParam() throws Exception {
        acService.changeAcTemp(1,56,1);
    }
}