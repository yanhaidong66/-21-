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
    private BillService billService;
    @Autowired
    private void UserService(UserRepository userRepository,BillService billService){
        this.userRepository=userRepository;
        this.billService=billService;
    }

    public void createUser(User user)throws Exception{
        userRepository.addUser(user);
        BillItem billItem=new BillItem.Builder()
                .setUserId(user.getUserId())
                .setState(BillItem.BillState.NOT_PAY)
                .setCreateTime(LocalDateTime.now())
                .setAcId(user.getAcId())
                .build();
        billService.createBillItem(billItem);
    }

    public void deleteUser(int userId)throws Exception{
        User user = userRepository.getUser(userId);
        BillItem billItem=new BillItem.Builder()
                .setUserId(userId)
                .setState(BillItem.BillState.HAVE_PAY)
                .setCreateTime(LocalDateTime.now())
                .setAcId(user.getAcId())
                .setCost(billService.getCost(userId))
                .build();
        billService.createBillItem(billItem);
        userRepository.deleteUser(userId);

    }
    public People getUser(int userId)throws Exception{
        return userRepository.getUser(userId);
    }
    public User getUser(String username)throws Exception{return userRepository.getUser(username);}
    public Waiter getWaiter(String username)throws Exception{return userRepository.getWaiter(username);}
    public Manager getManager(String username)throws Exception{return userRepository.getManager(username);}
    public Admin getAdmin(String username)throws Exception{return userRepository.getAdmin(username);}
    public List<User> getAllUser()throws Exception{
        return userRepository.getAllUser();
    }
}
