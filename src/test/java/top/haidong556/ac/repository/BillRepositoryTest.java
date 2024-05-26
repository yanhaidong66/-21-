package top.haidong556.ac.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.bill.BillItem;
import top.haidong556.ac.mapper.BillMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {
        BillRepository.class,
        BillMapper.class
})
class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillMapper billMapper;

    private BillItem billItem;

    @BeforeEach
    void setUp() {
        billItem = new BillItem();
        billItem.setBill_id(1);
        billItem.setState(BillItem.BillState.NOT_PAY);
        billItem.setDatetime(LocalDateTime.now());
        billItem.setUserId(1);
        billItem.setAcId(1);

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
        BillItem newBillItem = new BillItem();
        newBillItem.setState(BillItem.BillState.HAVE_PAY);
        newBillItem.setDatetime(LocalDateTime.now());
        newBillItem.setUserId(2);
        newBillItem.setAcId(2);

        billRepository.createBillItem(newBillItem);

        List<BillItem> billItems = billRepository.getBillItemByTime(newBillItem.getDatetime().minusMinutes(1), newBillItem.getDatetime().plusMinutes(1));

        assertNotNull(billItems);
        assertTrue(billItems.stream().anyMatch(bill -> bill.getUserId() == 2));
    }

    @Test
    void deleteBillItem() {
        billRepository.deleteBillItem(billItem.getBill_id());

        List<BillItem> billItems = billRepository.getBillItemByTime(billItem.getDatetime().minusMinutes(1), billItem.getDatetime().plusMinutes(1));

        assertTrue(billItems.isEmpty());
    }
}