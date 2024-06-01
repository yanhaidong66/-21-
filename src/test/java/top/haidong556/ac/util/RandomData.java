package top.haidong556.ac.util;

import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.role.User;

import java.util.Random;
import java.util.UUID;

public class RandomData {

    public static Ac getRandomAc(){
        Random random = new Random();
        Ac ac=new Ac();
        ac.setRoom(UUID.randomUUID().toString().replace("-", "").substring(0, 4));
        ac.setTemp(random.nextInt(0,30));
        if(random.nextBoolean()==true)
            ac.setAcState(Ac.AcState.OPEN);
        else
            ac.setAcState(Ac.AcState.CLOSE);
        ac.setWindSpeed(random.nextInt(1,3));
        return ac;
    }
    public static User getRandomUser(int acId){
        User user = new User(UUID.randomUUID().toString().replace("-", "").substring(0, 10), "password1", acId);
        user.setAcId(acId);
        return user;
    }
}
