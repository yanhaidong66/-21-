package top.haidong556.ac.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.mapper.AcMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest( classes = {
        AcRepository.class,
        Ac.class,
        AcMapper.class
})
class AcRepositoryTest {

    @Autowired
    AcRepository acRepository;

    private Ac ac;

    @BeforeEach
    void init() {
        ac = new Ac();
    }

    @Test
    void addAc() {
        ac.setAcId(1);
        ac.setWindSpeed(3);
        ac.setTemp(24);
        ac.setRoom("Living Room");
        ac.setCostPerHour(10);
        ac.setAcState(Ac.AcState.CLOSE);

        acRepository.addAc(ac);

        Ac retrievedAc = acRepository.getAcState(ac.getAcId());
        assertEquals(1, retrievedAc.getAcId());
        assertEquals(3, retrievedAc.getWindSpeed());
        assertEquals(24, retrievedAc.getTemp());
        assertEquals("Living Room", retrievedAc.getRoom());
        assertEquals(10, retrievedAc.getCostPerHour());
        assertEquals(Ac.AcState.CLOSE, retrievedAc.getAcState());
    }

    @Test
    void getAcState() {
        ac.setAcId(8);
        ac.setWindSpeed(3);
        ac.setTemp(24);
        ac.setRoom("Bedroom");
        ac.setCostPerHour(10);
        ac.setAcState(Ac.AcState.OPEN);

        acRepository.addAc(ac);

        Ac retrievedAc = acRepository.getAcState(ac.getAcId());
        assertNotNull(retrievedAc);
        assertEquals(3, retrievedAc.getWindSpeed());
        assertEquals(24, retrievedAc.getTemp());
        assertEquals("Bedroom", retrievedAc.getRoom());
        assertEquals(10, retrievedAc.getCostPerHour());
        assertEquals(Ac.AcState.OPEN, retrievedAc.getAcState());
    }

    @Test
    void closeAc() {
        ac.setWindSpeed(2);
        ac.setTemp(26);
        ac.setRoom("Study Room");
        ac.setCostPerHour(8);
        ac.setAcState(Ac.AcState.OPEN);

        acRepository.addAc(ac);

        acRepository.closeAc(ac.getAcId());

        Ac retrievedAc = acRepository.getAcState(ac.getAcId());

        assertEquals(Ac.AcState.CLOSE, retrievedAc.getAcState());
    }

    @Test
    void openAc() {
        ac.setAcId(4);
        ac.setWindSpeed(2);
        ac.setTemp(26);
        ac.setRoom("Guest Room");
        ac.setCostPerHour(8);
        ac.setAcState(Ac.AcState.CLOSE);

        acRepository.addAc(ac);

        acRepository.openAc(ac.getAcId());

        Ac retrievedAc = acRepository.getAcState(ac.getAcId());

        assertEquals(Ac.AcState.OPEN, retrievedAc.getAcState());
    }

    @Test
    void changeAcWindSpeed() {
        ac.setAcId(5);
        ac.setWindSpeed(2);
        ac.setTemp(22);
        ac.setRoom("Office");
        ac.setCostPerHour(12);
        ac.setAcState(Ac.AcState.OPEN);

        acRepository.addAc(ac);

        acRepository.changeAcWindSpeed(ac.getAcId(), 5);

        Ac retrievedAc = acRepository.getAcState(ac.getAcId());

        assertEquals(5, retrievedAc.getWindSpeed());
    }

    @Test
    void changeAcTemp() {
        ac.setAcId(6);
        ac.setWindSpeed(1);
        ac.setTemp(20);
        ac.setRoom("Dining Room");
        ac.setCostPerHour(15);
        ac.setAcState(Ac.AcState.CLOSE);

        acRepository.addAc(ac);

        acRepository.changeAcTemp(ac.getAcId(), 18);

        Ac retrievedAc = acRepository.getAcState(ac.getAcId());

        assertEquals(18, retrievedAc.getTemp());
    }

    @Test
    void getAllAcState() {
        List<Ac> allAcState = acRepository.getAllAcState();
        System.out.println(allAcState);
    }
}
