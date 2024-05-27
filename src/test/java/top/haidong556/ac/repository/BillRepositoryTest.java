package top.haidong556.ac.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.bill.BillItem;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.mapper.BillMapper;
import top.haidong556.ac.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {
        BillRepository.class,
        BillMapper.class,
        BillItem.class,
        UserRepository.class,
        UserMapper.class
})
class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserRepository userRepository;
    private BillItem billItem;

    @BeforeEach
    void setUp() {
        billItem = new BillItem();
        billItem.setState(BillItem.BillState.NOT_PAY);
        billItem.setCreateTime(LocalDateTime.now());
        billItem.setUserId(2);
        billItem.setAcId(2);

        billRepository.createBillItem(billItem);
    }

    @Test
    void getBillItemByTime() {
        LocalDateTime startTime = LocalDateTime.now().minusDays(1);
        LocalDateTime endTime = LocalDateTime.now();

        List<BillItem> billItems = billRepository.getBillItemByTime(startTime, endTime);

        assertNotNull(billItems);
        assertFalse(billItems.isEmpty());
        assertEquals(1, billItems.size());
    }

    @Test
    void createBillItem() {

        billRepository.createBillItem(billItem);

        List<BillItem> billItems = billRepository.getBillItemByTime(billItem.getCreateTime().minusMinutes(1),billItem.getCreateTime().plusMinutes(1));

        assertNotNull(billItems);
        assertTrue(billItems.stream().anyMatch(bill -> bill.getUserId() == 2));
    }

    @Test
    void deleteBillItem() {
        billRepository.deleteBillItem(billItem.getBillId());

        List<BillItem> billItems = billRepository.getBillItemByTime(billItem.getCreateTime().minusMinutes(1), billItem.getCreateTime().plusMinutes(1));

        assertTrue(billItems.isEmpty());
    }
}