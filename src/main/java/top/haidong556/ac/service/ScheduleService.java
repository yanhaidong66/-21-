package top.haidong556.ac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public abstract class ScheduleService implements Runnable {
    protected AcService acService;
    public ScheduleService(AcService acService){
        this.acService=acService;
    }
    public abstract void changeAcTemp(int acId, int newTemp);

    public abstract void changeAcWindSpeed(int acId, int newWindSpeed);

    public abstract void closeAc(int acId);

    public abstract void openAc(int acId);
}
