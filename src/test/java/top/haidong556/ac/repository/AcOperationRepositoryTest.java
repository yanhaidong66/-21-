package top.haidong556.ac.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.mapper.AcOperationMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = { AcOperationRepository.class, AcOperationMapper.class ,UserRepository.class,AcRepository.class})
class AcOperationRepositoryTest {

    @Autowired
    private AcOperationRepository acOperationRepository;
    @Autowired
    private AcRepository acRepository;
    @Autowired
    private UserRepository userRepository;
    private OperationItem operationItem;
    private User user;
    private Ac ac;
    @BeforeEach
    void setupTestData() {
        ac = new Ac();
        ac.setWindSpeed(3);
        ac.setTemp(24);
        ac.setRoom(UUID.randomUUID().toString().replace("-", "").substring(0, 4));
        ac.setAcState(Ac.AcState.CLOSE);
        acRepository.addAc(ac);
        user = new User(UUID.randomUUID().toString().replace("-", "").substring(0, 10), "password1", ac.getAcId());
        userRepository.addUser(user);

        OperationItem operationItem = new OperationItem.Builder()
                .setUserId(user.getUserId())
                .setType(OperationItem.OperationType.OPEN_AC)
                .setCreateTime(LocalDateTime.now())
                .setAcId(ac.getAcId())
                .build();
        acOperationRepository.createOperationItem(operationItem);
    }

    @AfterEach
    void cleanupTestData() {
        acOperationRepository.deleteOperationItem(operationItem.getOperationId());
        userRepository.deleteUser(user.getUserId());
        acRepository.deleteAc(ac.getAcId());
    }

    @Test
    void getAcOperationTableByUserId() {
        List<OperationItem> result = acOperationRepository.getAcOperationTableByUserId(user.getUserId());
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void getAcOperationTableByAcId() {
        int acId = operationItem.getAcId();
        List<OperationItem> result = acOperationRepository.getAcOperationTableByAcId(acId);

        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void createOperationItem() {
        List<OperationItem> result = acOperationRepository.getAcOperationTableByUserId(operationItem.getUserId());
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(operationItem.getType(), result.get(0).getType());
    }
}