package top.haidong556.ac.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.mapper.AcOperationMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {
        AcOperationRepository.class,
        AcOperationMapper.class
})
class AcOperationRepositoryTest {

    @Autowired
    private AcOperationRepository acOperationRepository;

    @Test
    void getAcOperationTableByUserId() {
        int userId = 1;
        List<OperationItem> result = acOperationRepository.getAcOperationTableByUserId(userId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(OperationItem.OperationType.OPEN_AC, result.get(0).getType());
        assertEquals(OperationItem.OperationType.CLOSE_AC, result.get(1).getType());
    }

    @Test
    void getAcOperationTableByAcId() {
        int acId = 101;
        List<OperationItem> result = acOperationRepository.getAcOperationTableByAcId(acId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(OperationItem.OperationType.OPEN_AC, result.get(0).getType());
        assertEquals(OperationItem.OperationType.CLOSE_AC, result.get(1).getType());
    }

    @Test
    void createOperationItem() {
        OperationItem newItem = new OperationItem();
        newItem.setUserId(2);
        newItem.setType(OperationItem.OperationType.CHANGE_AC_WIND_SPEED);
        newItem.setCreateTime(LocalDateTime.now());
        newItem.setAcId(3);

        acOperationRepository.createOperationItem(newItem);

        List<OperationItem> result = acOperationRepository.getAcOperationTableByUserId(newItem.getUserId());
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(OperationItem.OperationType.CHANGE_AC_WIND_SPEED, result.get(0).getType());
    }
}