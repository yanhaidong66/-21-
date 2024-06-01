package top.haidong556.ac.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.bill.BillItem;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.mapper.BillMapper;
import top.haidong556.ac.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {
        BillRepository.class,
        UserRepository.class,
        AcRepository.class
})
class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AcRepository acRepository;
    private BillItem billItem;
    private User user;
    private Ac ac;

    @BeforeEach
    void setUp() {
        ac = new Ac();
        ac.setWindSpeed(3);
        ac.setTemp(24);
        ac.setRoom(UUID.randomUUID().toString().replace("-", "").substring(0, 4));
        ac.setAcState(Ac.AcState.CLOSE);
        acRepository.addAc(ac);

        user = new User(UUID.randomUUID().toString().replace("-", "").substring(0, 10), "password1", ac.getAcId());
        userRepository.addUser(user);

        billItem = new BillItem.Builder()
                .setState(BillItem.BillState.NOT_PAY)
                .setCreateTime(LocalDateTime.now())
                .setUserId(user.getUserId())
                .setAcId(ac.getAcId())
                .build();

        billRepository.createBillItem(billItem);
    }

    @AfterEach
    void tearDown() {
        billRepository.deleteBillItem(billItem.getBillId());
        userRepository.deleteUser(user.getUserId());
        acRepository.deleteAc(ac.getAcId());
    }

    @Test
    void getBillItemByTime() {
        LocalDateTime startTime = LocalDateTime.now().minusDays(3);
        LocalDateTime endTime = LocalDateTime.now();

        List<BillItem> billItems = billRepository.getBillItemByTime(startTime, endTime);

        assertNotNull(billItems);
        System.out.println(billItems);

    }

    @Test
    void createBillItem() {
        List<BillItem> billItems = billRepository.getBillItemByTime(billItem.getCreateTime().minusMinutes(1),billItem.getCreateTime().plusMinutes(1));
        assertNotNull(billItems);
        assertTrue(billItems.stream().anyMatch(bill -> bill.getUserId() == user.getUserId()));
    }

    @Test
    void deleteBillItem() {
        billRepository.deleteBillItem(billItem.getBillId());

        List<BillItem> billItems = billRepository.getBillItemByTime(billItem.getCreateTime().minusMinutes(1), billItem.getCreateTime().plusMinutes(1));

        assertTrue(billItems.isEmpty());
    }


}