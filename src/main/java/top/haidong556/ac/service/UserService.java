package top.haidong556.ac.service;

import top.haidong556.ac.entity.bill.BillItem;
import top.haidong556.ac.entity.role.*;
import top.haidong556.ac.repository.BillRepository;
import top.haidong556.ac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private BillRepository billRepository;
    @Autowired
    private void UserService(UserRepository userRepository,BillRepository billRepository){
        this.userRepository=userRepository;
        this.billRepository=billRepository;
    }

    public void createUser(User user){
        userRepository.addUser(user);
        BillItem billItem=new BillItem.Builder()
                .setAcId(user.getUserId())
                .setState(BillItem.BillState.NOT_PAY)
                .setCreateTime(LocalDateTime.now())
                .setAcId(user.getAcId())
                .build();
        billRepository.createBillItem(billItem);
    }

    public void deleteUser(int userId){
        User user = userRepository.getUser(userId);
        userRepository.deleteUser(userId);
        BillItem billItem=new BillItem.Builder()
                .setAcId(user.getUserId())
                .setState(BillItem.BillState.HAVE_PAY)
                .setCreateTime(LocalDateTime.now())
                .setAcId(user.getAcId())
                .build();
        billRepository.createBillItem(billItem);
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
