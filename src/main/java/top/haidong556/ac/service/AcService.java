package top.haidong556.ac.service;

import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.repository.AcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcService {
    private AcRepository acRepository;
    @Autowired
    private void setAcRepository(){}


    public void addAc(Ac ac){
        acRepository.addAc(ac);
    }

    public void changeAcTemp(int acId,int newTemp){

    }


    public void changeAcWindSpeed(int acId,int newWindSpeed){

    }

    public void closeAc(int acId){

    }

    public void openAc(int acId){

    }

    public void getAcDetailTable(int acId){

    }
}
