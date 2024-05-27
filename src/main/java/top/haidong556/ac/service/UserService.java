package top.haidong556.ac.service;

import top.haidong556.ac.entity.role.*;
import top.haidong556.ac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    private void UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void createUser(People user){
        userRepository.addUser(user);
    }

    public void deleteUser(int userId){
        userRepository.deleteUser(userId);
    }
    public People getUser(int userId){
        return userRepository.getUser(userId);
    }
    public User getUser(String username){return userRepository.getUser(username);}
    public Waiter getWaiter(String username){return userRepository.getWaiter(username);}
    public Manager getManager(String username){return userRepository.getManager(username);}
    public Admin getAdmin(String username){return userRepository.getAdmin(username);}
    public List<User> getAllUser(){
        return userRepository.getAllUser();
    }
}
