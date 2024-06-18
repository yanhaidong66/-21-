package top.haidong556.ac.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.role.Manager;
import top.haidong556.ac.entity.role.People;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.mapper.AcMapper;
import top.haidong556.ac.mapper.UserMapper;
import top.haidong556.ac.util.RandomData;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {
        UserRepository.class,
        AcRepository.class,
        User.class,
        UserMapper.class
})
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AcRepository acRepository;
    List<User> users=new LinkedList<>();
    List<Ac> acs=new LinkedList<>();

    @BeforeEach
    void setupTestData() throws Exception {
        for (int i = 0; i < 100; i++) {
            Ac ac = RandomData.getRandomAc();
            acs.add(ac);
            acRepository.addAc(ac);
            System.out.println("add ac "+ i);
            User user = RandomData.getRandomUser(ac.getAcId());
            userRepository.addUser(user);
            System.out.println("add user "+i);
        }

    }

    @AfterEach
    void cleanupTestData() throws Exception {
        for (User user:users) {
            userRepository.deleteUser(user.getUserId());
        }
        for(Ac ac:acs)
            acRepository.deleteAc(ac.getAcId());
    }

    @Test
    void addUser() throws Exception {

    }

    @Test
    void deleteUser() throws Exception {

    }

    @Test
    void getUserById() throws Exception {
        for (User user :
                users) {
            User user1=userRepository.getUser(user.getUserId());
            assertNotNull(user1);
            assertEquals(user1.getUsername(), user.getUsername());
        }

    }

    @Test
    void getUserByUsername() throws Exception {
        for (User user :
                users) {
            User user1=userRepository.getUser(user.getUsername());
            assertNotNull(user1);
            assertEquals(user1.getUsername(), user.getUsername());
        }
    }

    @Test
    void getAllUser() throws Exception {
        List<User> allUsers = userRepository.getAllUser();
        System.out.println(allUsers);
    }
}