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
    private User user1;
    private User user2;
    private Ac ac;

    @BeforeEach
    void setupTestData() throws Exception {
        ac = new Ac();
        ac.setWindSpeed(3);
        ac.setTemp(24);
        ac.setRoom("2342322");
        ac.setAcState(Ac.AcState.CLOSE);
        acRepository.addAc(ac);
        user1 = new User("testUser918", "password1", ac.getAcId());
        user2 = new User("testUser919", "password2", ac.getAcId());
        userRepository.addUser(user1);
        userRepository.addUser(user2);
    }

    @AfterEach
    void cleanupTestData() throws Exception {
        userRepository.deleteUser(user1.getUserId());
        userRepository.deleteUser(user2.getUserId());
        acRepository.deleteAc(ac.getAcId());
    }

    @Test
    void addUser() throws Exception {

        User retrievedUser = userRepository.getUser(user1.getUserId());
        assertNotNull(retrievedUser);
        assertEquals(user1.getUsername(), retrievedUser.getUsername());
    }

    @Test
    void deleteUser() throws Exception {

        userRepository.deleteUser(user1.getUserId());
        User deletedUser = userRepository.getUser(user1.getUserId());
        assertNull(deletedUser);
    }

    @Test
    void getUserById() throws Exception {
        List<User> allUsers = userRepository.getAllUser();
        User userToRetrieve = allUsers.get(0);

        User retrievedUser = userRepository.getUser(userToRetrieve.getUserId());
        assertNotNull(retrievedUser);
        assertEquals(userToRetrieve.getUsername(), retrievedUser.getUsername());
    }

    @Test
    void getUserByUsername() throws Exception {
        List<User> allUsers = userRepository.getAllUser();
        User userToRetrieve = allUsers.get(0);

        User retrievedUser = userRepository.getUser(userToRetrieve.getUsername());
        assertNotNull(retrievedUser);
        assertEquals(userToRetrieve.getUsername(), retrievedUser.getUsername());
    }

    @Test
    void getAllUser() throws Exception {
        List<User> allUsers = userRepository.getAllUser();
        System.out.println(allUsers);
    }
}