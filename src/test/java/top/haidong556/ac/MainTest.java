package top.haidong556.ac;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.service.AcService;
import top.haidong556.ac.service.UserService;
import top.haidong556.ac.util.GlobalConfig;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

@SpringBootTest
public class MainTest {
    @Autowired
    public MainTest(AcService acService,UserService userService){
        this.userService=userService;
        this.acService=acService;
    }
    private AcService acService;
    private UserService userService;
    List<Ac> acs=new LinkedList<>();
    List<User> users=new LinkedList<>();
    //房间初始温度
    //	房间一	32
    //	房间二	28
    //	房间三	30
    //	房间四	29
    //	房间五	35
    @BeforeEach
    public void initData() throws Exception {
        createAcAndUser(32, Ac.AcWindSpeed.MID, "房间一", "用户一");
        createAcAndUser(28,Ac.AcWindSpeed.MID, "房间二", "用户二");
        createAcAndUser(30,Ac.AcWindSpeed.MID, "房间三", "用户三");
        createAcAndUser(29,Ac.AcWindSpeed.MID, "房间四", "用户四");
        createAcAndUser(35,Ac.AcWindSpeed.MID, "房间五", "用户五");
    }

    private void createAcAndUser(int temp, Ac.AcWindSpeed windSpeed, String room, String username) throws Exception {
        Ac ac = new Ac();
        ac.setTemp(temp);
        ac.setRoom(room);
        ac.setAcState(Ac.AcState.CLOSE);
        ac.setWindSpeed(windSpeed);
        acService.addAc(ac);
        acs.add(ac);

        User user = new User();
        user.setAcId(ac.getAcId());
        user.setUsername(username);
        user.setPassword("password");
        userService.createUser(user);
        users.add(user);
    }
    @AfterEach
    public void afterClearData() throws Exception {
        for (User user :
                users) {
            userService.deleteUser(user.getUserId());
        }
        for (Ac ac :
                acs) {
            acService.deleteAc(ac.getAcId());
        }
    }
    //检查程序启动，进入设置环节：设置模式和工作温度，然后启动空调服务；测试工作温度是否可以低于17度或高于26度
    //根据老师给的表，进行调度，输出到文件中
    @Test
    void mainTest()  {
        int perSecondMillisecond = GlobalConfig.PER_SECOND_MILLISECOND;
        int perMinuteMillisecond = perSecondMillisecond*60;
        try{
            Ac acState = acService.getAcState(acs.get(0).getAcId());
            System.out.println(acState);
            acService.changeAcTemp(acState.getAcId(),38,GlobalConfig.SYSTEM_ID);
            Ac acState1 = acService.getAcState(acState.getAcId());
            System.out.println(acState1);
            acService.changeAcTemp(acState.getAcId(),acState.getTemp(),GlobalConfig.SYSTEM_ID);

//        房间1	房间3	房间3	房间4	房间5
//
//中央空调启动			检查程序启动，进入设置环节：设置模式和工作温度，然后启动空调服务；测试工作温度是否可以低于17度或高于26度
//开机
//18	开机			开机
//		开机
//	19		开机
//				22
//高
//	关机
//	开机			高
//
//22			18，高
//
//	22
//				低
//
//关机		24，低
//				20，高
//	关机
//		高
//开机			20，中
//	开机
//				25
//
//		关机
//				关机
//关机
//	关机		关机
//
//        sleep(perMinuteMillisecond*1);
//
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
//        sleep(perMinuteMillisecond*1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println();
        }





    }
}
