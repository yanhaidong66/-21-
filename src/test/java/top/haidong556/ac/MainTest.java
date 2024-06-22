package top.haidong556.ac;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.service.AcService;
import top.haidong556.ac.service.BillService;
import top.haidong556.ac.service.ScheduleService;
import top.haidong556.ac.service.UserService;
import top.haidong556.ac.util.GlobalConfig;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

@SpringBootTest
public class MainTest {
    @Autowired
    public MainTest(AcService acService,UserService userService,BillService billService,ScheduleService scheduleService){
        this.userService=userService;
        this.acService=acService;
        this.billService=billService;
        this.scheduleService=scheduleService;
    }
    private AcService acService;
    private UserService userService;
    private ScheduleService scheduleService;

    private BillService billService;
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
    public  void  outputAllACStatus() throws Exception {

        for (int i=0;i<5;i++) {
            int acId = acs.get(i).getAcId();
            Ac acState = acService.getAcState(acId);
            acs.set(i,acState);
            System.out.println(acState);
        }
        System.out.println("--------------------------------------------------------");
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
//            Ac acState = acService.getAcState(acs.get(0).getAcId());
//            System.out.println(acState);
//            acService.changeAcTemp(acState.getAcId(),38,GlobalConfig.SYSTEM_ID);
//            Ac acState1 = acService.getAcState(acState.getAcId());
//            System.out.println(acState1);
//            acService.changeAcTemp(acState.getAcId(),acState.getTemp(),GlobalConfig.SYSTEM_ID);

            System.out.println(users);
            System.out.println("----------");
            scheduleService.openAc(acs.get(0).getAcId(),acs.get(0).getWindSpeed(),users.get(0).getUserId());
            sleep(perMinuteMillisecond*1);//1
            outputAllACStatus();
            acService.changeAcTemp(acs.get(1).getAcId(),18,users.get(1).getUserId());
            scheduleService.openAc(acs.get(1).getAcId(),acs.get(1).getWindSpeed(),users.get(1).getUserId());
            scheduleService.openAc(acs.get(4).getAcId(),acs.get(4).getWindSpeed(),users.get(4).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            scheduleService.openAc(acs.get(2).getAcId(),acs.get(2).getWindSpeed(),users.get(2).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            acService.changeAcTemp(acs.get(1).getAcId(),19,users.get(1).getUserId());
            scheduleService.openAc(acs.get(3).getAcId(),acs.get(3).getWindSpeed(),users.get(3).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            acService.changeAcTemp(acs.get(4).getAcId(),22,users.get(4).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            scheduleService.changeAcWindSpeed(acs.get(0).getAcId(), 3,users.get(0).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            scheduleService.closeAc(acs.get(2).getAcId(),users.get(2).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            scheduleService.openAc(acs.get(1).getAcId(),acs.get(1).getWindSpeed(),users.get(1).getUserId());
            scheduleService.changeAcWindSpeed(acs.get(4).getAcId(),3,users.get(4).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            acService.changeAcTemp(acs.get(0).getAcId(),22,users.get(0).getUserId());
            acService.changeAcTemp(acs.get(3).getAcId(),18,users.get(3).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            sleep(perMinuteMillisecond*1);
            outputAllACStatus();







            acService.changeAcTemp(acs.get(1).getAcId(),22,users.get(1).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();
            scheduleService.changeAcWindSpeed(acs.get(4).getAcId(), 1,users.get(4).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();
            scheduleService.closeAc(acs.get(0).getAcId(),users.get(0).getUserId());
            acService.changeAcTemp(acs.get(2).getAcId(),24,users.get(2).getUserId());
            scheduleService.changeAcWindSpeed(acs.get(2).getAcId(), 1,users.get(2).getUserId());

            sleep(perMinuteMillisecond*1);
            outputAllACStatus();
            acService.changeAcTemp(acs.get(4).getAcId(),20,users.get(4).getUserId());
            scheduleService.changeAcWindSpeed(acs.get(4).getAcId(), 3,users.get(4).getUserId());

            sleep(perMinuteMillisecond*1);
            outputAllACStatus();










            scheduleService.closeAc(acs.get(1).getAcId(),users.get(1).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            scheduleService.changeAcWindSpeed(acs.get(2).getAcId(), 3,users.get(2).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            scheduleService.openAc(acs.get(0).getAcId(),acs.get(0).getWindSpeed(),users.get(0).getUserId());
            acService.changeAcTemp(acs.get(3).getAcId(),20,users.get(3).getUserId());
            scheduleService.changeAcWindSpeed(acs.get(3).getAcId(), 2,users.get(3).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();

            scheduleService.openAc(acs.get(1).getAcId(),acs.get(1).getWindSpeed(),users.get(1).getUserId());
            sleep(perMinuteMillisecond*1);//1
            outputAllACStatus();

            acService.changeAcTemp(acs.get(4).getAcId(),25,users.get(4).getUserId());
            sleep(perMinuteMillisecond*1);
            outputAllACStatus();






            sleep(perMinuteMillisecond*1);//1
            scheduleService.closeAc(acs.get(2).getAcId(),users.get(2).getUserId());
            sleep(perMinuteMillisecond*1);
            scheduleService.closeAc(acs.get(4).getAcId(),users.get(4).getUserId());
            sleep(perMinuteMillisecond*1);
            scheduleService.closeAc(acs.get(0).getAcId(),users.get(0).getUserId());
            sleep(perMinuteMillisecond*1);
            scheduleService.closeAc(acs.get(1).getAcId(),users.get(1).getUserId());
            scheduleService.closeAc(acs.get(3).getAcId(),users.get(3).getUserId());
            sleep(perMinuteMillisecond*1);






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
