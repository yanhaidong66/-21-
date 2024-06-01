package top.haidong556.ac.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.mapper.AcMapper;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = { AcRepository.class, Ac.class, AcMapper.class })
class AcRepositoryTest {

    @Autowired
    AcRepository acRepository;
    private Ac ac;

    @BeforeEach
    void setupTestData() {
        ac = new Ac();
        ac.setWindSpeed(3);
        ac.setTemp(24);
        ac.setRoom(UUID.randomUUID().toString().replace("-", "").substring(0, 3));
        ac.setAcState(Ac.AcState.OPEN);
        acRepository.addAc(ac);
    }

    @AfterEach
    void cleanupTestData() {
        acRepository.deleteAc(ac.getAcId());
    }

    @Test
    void getAcState() {
        Ac retrievedAc = acRepository.getAcState(ac.getAcId());
        assertNotNull(retrievedAc);
        assertEquals(ac.getWindSpeed(), retrievedAc.getWindSpeed());
        assertEquals(ac.getTemp(), retrievedAc.getTemp());
        assertEquals(ac.getRoom(), retrievedAc.getRoom());
        while (true){
            retrievedAc = acRepository.getAcState(ac.getAcId());
            System.out.println("retrievedAcState"+retrievedAc.getAcState());
        }
    }

    @Test
    void closeAc() {
        acRepository.closeAc(ac.getAcId());

        Ac retrievedAc = acRepository.getAcState(ac.getAcId());
        assertEquals(Ac.AcState.CLOSE, retrievedAc.getAcState());
    }

    @Test
    void openAc() {
        acRepository.openAc(ac.getAcId());
        Ac retrievedAc = acRepository.getAcState(ac.getAcId());
        assertEquals(Ac.AcState.OPEN, retrievedAc.getAcState());
    }

    @Test
    void changeAcWindSpeed() {
        acRepository.changeAcWindSpeed(ac.getAcId(), 5);

        Ac retrievedAc = acRepository.getAcState(ac.getAcId());
        assertEquals(5, retrievedAc.getWindSpeed());
    }

    @Test
    void changeAcTemp() {
        acRepository.changeAcTemp(ac.getAcId(), 18);

        Ac retrievedAc = acRepository.getAcState(ac.getAcId());
        assertEquals(18, retrievedAc.getTemp());
    }

    @Test
    void getAllAcState() {
        List<Ac> allAcStates = acRepository.getAllAcState();
        for(Ac ac:allAcStates){
            System.out.println(ac);
        }
    }
}
