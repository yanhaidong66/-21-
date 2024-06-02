package top.haidong556.ac.service;


import lombok.Data;

import java.util.Date;
@Data
public class ServiceObject {
    private  int acId;
    private  double currentTemp;
    private  double targetTemp;
    private  int windSpeed;
    private  int mode;  //加热还是制冷,0加热，1制冷
    private Date createTime;
    private  Date startTime;
    private  int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public ServiceObject(int acId) {
        this.acId = acId;
        currentTemp=25;
        targetTemp=23;
        windSpeed=1;
        mode=1;
    }

    public  void startService(){
        startTime=new Date();

    }

}
