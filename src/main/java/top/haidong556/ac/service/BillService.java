package top.haidong556.ac.service;

import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.bill.BillItem;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.repository.AcOperationRepository;
import top.haidong556.ac.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.haidong556.ac.util.GlobalConfig;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class BillService {
    private BillRepository billRepository;
    private AcOperationRepository acOperationRepository;

    @Autowired
    public BillService(BillRepository billRepository,AcOperationRepository acOperationRepository)throws Exception{
        this.billRepository=billRepository;
        this.acOperationRepository=acOperationRepository;
    }
    public List<BillItem> getBillItemByTime(LocalDateTime startTime, LocalDateTime endTime)throws Exception  {
        List<BillItem> billItemByTime = billRepository.getBillItemByTime(startTime, endTime);
        return billItemByTime;
    }

    // Create a new bill item
    public void createBillItem(BillItem billItem)throws Exception {
        billRepository.createBillItem(billItem);
    }

    // Delete a bill item by ID
    public void deleteBillItem(int billItemId)throws Exception {
        billRepository.deleteBillItem(billItemId);
    }

    public float getCost(int userId)throws Exception{
        float cost=0;
        long intervalMill=0;
        float intervalSecond=0;
        LocalDateTime openTime=null;
        LocalDateTime closeTime=null;
        float speedRate=1;
        Ac.AcState acState= Ac.AcState.CLOSE;
        List<OperationItem> acOperationTableByUserId = acOperationRepository.getAcOperationTableByUserId(userId);
        for(OperationItem operationItem:acOperationTableByUserId){
            switch (operationItem.getType()){
                case OPEN_AC:{
                    openTime=operationItem.getCreateTime();
                    acState= Ac.AcState.OPEN;
                    if(operationItem.getAcWindSpeed()==1){
                        speedRate=0.8f;
                    }
                    else if(operationItem.getAcWindSpeed()==2){
                        speedRate=1;
                    }
                    else if(operationItem.getAcWindSpeed()==3){
                        speedRate=1.2f;
                    }
                }break;
                case CLOSE_AC:{
                    closeTime=operationItem.getCreateTime();
                    if(acState== Ac.AcState.OPEN){
                        intervalMill= Duration.between(openTime.toInstant(ZoneOffset.UTC),closeTime.toInstant(ZoneOffset.UTC)).toMillis();
                        intervalSecond=intervalMill/GlobalConfig.PER_SECOND_MILLISECOND;
                        cost+=speedRate* GlobalConfig.AC_COST_PER_SECOND* intervalSecond;
                    }
                    acState= Ac.AcState.CLOSE;
                    openTime=null;


                }break;
                case CHANGE_AC_TEMP:{
                    ;
                }break;
                case CHANGE_AC_WIND_SPEED:{
                    if(acState== Ac.AcState.CLOSE)
                        break;
                    intervalMill= Duration.between(openTime.toInstant(ZoneOffset.UTC),operationItem.getCreateTime().toInstant(ZoneOffset.UTC)).toMillis();
                    intervalSecond=intervalMill/GlobalConfig.PER_SECOND_MILLISECOND;
                    cost+=speedRate* GlobalConfig.AC_COST_PER_SECOND* intervalSecond;
                    openTime=operationItem.getCreateTime();
                    if(operationItem.getAcWindSpeed()==1){
                        speedRate=0.8f;
                    }
                    else if(operationItem.getAcWindSpeed()==2){
                        speedRate=1;
                    }
                    else if(operationItem.getAcWindSpeed()==3){
                        speedRate=1.2f;
                    }
                }break;
            }
        }
        if(acState!= Ac.AcState.CLOSE){
            intervalMill= Duration.between(openTime.toInstant(ZoneOffset.UTC),LocalDateTime.now().toInstant(ZoneOffset.UTC)).toMillis();
            intervalSecond = intervalMill/GlobalConfig.PER_SECOND_MILLISECOND;
            cost+=speedRate* GlobalConfig.AC_COST_PER_SECOND* intervalSecond;
        }

        return cost;
    }


}
