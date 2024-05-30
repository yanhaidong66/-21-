package top.haidong556.ac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl extends ScheduleService{
    @Autowired
    public ScheduleServiceImpl(AcService acService) {
        super(acService);
    }
    @Override
    public void run() {

    }

    @Override
    public void changeAcTemp(int acId, int newTemp) {
        acService.changeAcTemp(acId,newTemp);
    }

    @Override
    public void changeAcWindSpeed(int acId, int newWindSpeed) {
        acService.changeAcWindSpeed(acId,newWindSpeed);
    }

    @Override
    public void closeAc(int acId) {
        acService.closeAc(acId);
    }

    @Override
    public void openAc(int acId) {
        acService.openAc(acId);
    }


}
