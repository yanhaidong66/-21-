package top.haidong556.ac.service;

import top.haidong556.ac.entity.role.People;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    private void setUserRepository(){}

    public void createUser(People user){
        userRepository.addUser(user);
    }

    public void deleteUser(int userId){
        userRepository.deleteUser(userId);
    }
    public People getUser(int userId){
        return userRepository.getUser(userId);
    }
    public List<User> getAllUser(){
        return userRepository.getAllUser();
    }
}
