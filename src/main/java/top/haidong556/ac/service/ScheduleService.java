package top.haidong556.ac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public abstract class ScheduleService implements Runnable {
    protected AcService acService;
    public ScheduleService(AcService acService){
        this.acService=acService;
    }
    public abstract void changeAcTemp(int acId, int newTemp,int userId);
    public abstract void changeAcWindSpeed(int acId, int newWindSpeed,int userId);
    public abstract void closeAc(int acId,int userId);
    public abstract void openAc(int acId,int userId);
    //可能添加mod，加热或者制冷
}
