package top.haidong556.ac.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.exception.DataBaseException;
import top.haidong556.ac.mapper.BillMapper;
import top.haidong556.ac.mapper.UserMapper;
import top.haidong556.ac.repository.BillRepository;
import top.haidong556.ac.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void getUserByAcId() throws DataBaseException {
        List<User> userByAcId = userService.getUserByAcId(15);
        System.out.println(userByAcId);
    }
}