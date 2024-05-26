package top.haidong556.ac.repository;

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
        User.class,
        UserMapper.class
})
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser() {
        User people = new User("testUser", "password",1);
        userRepository.addUser(people);

        User retrievedUser = userRepository.getUser( people.getUserId());
        assertNotNull(retrievedUser);
        assertEquals("testUser", retrievedUser.getUsername());
    }

    @Test
    void deleteUser() {
        People people = new User("testUser", "password",1);
        userRepository.addUser(people);
        int userId = ((User) people).getUserId();
        userRepository.deleteUser(userId);
        People retrievedUser = userRepository.getUser(userId);
        assertNull(retrievedUser);
    }

    @Test
    void getUserById() {
        People people = new User("testUser", "password",1);
        userRepository.addUser(people);
        int userId = ((User) people).getUserId();
        People retrievedUser = userRepository.getUser(userId);
        assertNotNull(retrievedUser);
        assertEquals("testUser", retrievedUser.getUsername());
    }

    @Test
    void getUserByUsername() {
        People people = new User("testUser23", "password",3);
        userRepository.addUser(people);
        People retrievedUser = userRepository.getUser("testUser23");
        assertNotNull(retrievedUser);
        assertEquals("testUser23", retrievedUser.getUsername());
    }

    @Test
    void getAllUser() {
        People people1 = new User("testUser12", "password1",2);
        People people2 = new User("testUser23", "password2",5);
        userRepository.addUser(people1);
        userRepository.addUser(people2);
        List<User> allUsers = userRepository.getAllUser();
        assertEquals(2, allUsers.size());
    }
}