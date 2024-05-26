package top.haidong556.ac.service;

import top.haidong556.ac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    private void setUserRepository(){}

    public void createUser(){

    }
    public void createManager(){

    }
    public void createWaiter(){

    }
    public void deleteUser(){

    }
    public void deleteManager(){

    }
    public void deleteWaiter(){

    }
}
