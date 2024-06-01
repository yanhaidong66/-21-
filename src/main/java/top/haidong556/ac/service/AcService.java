package top.haidong556.ac.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.entity.role.People;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.entity.role.UserDetailsAdapter;
import top.haidong556.ac.repository.AcOperationRepository;
import top.haidong556.ac.repository.AcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.haidong556.ac.security.MyUserDetailsServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AcService {
    private final AcRepository acRepository;
    private final AcOperationRepository acOperationRepository;

    @Autowired
    public AcService(AcRepository acRepository, AcOperationRepository acOperationRepository) {
        this.acRepository = acRepository;
        this.acOperationRepository = acOperationRepository;
    }

    public void addAc(Ac ac)throws Exception {
        acRepository.addAc(ac);
        System.out.println("添加空调设备："+ac);
    }

    public void changeAcTemp(int acId, int newTemp,int userId)throws Exception {
        acRepository.changeAcTemp(acId, newTemp);
        System.out.println("修改空调id="+acId+"温度为"+newTemp);
        Ac acState = acRepository.getAcState(acId);
        OperationItem operationItem=new OperationItem.Builder()
                .setAcId(acId)
                .setType(OperationItem.OperationType.CHANGE_AC_TEMP)
                .setAcTemp(acState.getTemp())
                .setAcWindSpeed(acState.getWindSpeed())
                .setUserId(userId)
                .setCreateTime(LocalDateTime.now())
                .build();
        acOperationRepository.createOperationItem(operationItem);
    }

    public void changeAcWindSpeed(int acId, int newWindSpeed,int userId)throws Exception {
        acRepository.changeAcWindSpeed(acId, newWindSpeed);
        Ac acState = acRepository.getAcState(acId);
        OperationItem operationItem=new OperationItem.Builder()
                .setAcId(acId)
                .setType(OperationItem.OperationType.CHANGE_AC_WIND_SPEED)
                .setUserId(userId)
                .setAcTemp(acState.getTemp())
                .setAcWindSpeed(acState.getWindSpeed())
                .setCreateTime(LocalDateTime.now())
                .build();
        acOperationRepository.createOperationItem(operationItem);
        System.out.println("修改空调"+acId+"风速为："+newWindSpeed);
    }

    public void closeAc(int acId,int userId)throws Exception {
        acRepository.closeAc(acId);
        Ac acState = acRepository.getAcState(acId);
        OperationItem operationItem=new OperationItem.Builder()
                .setAcId(acId)
                .setType(OperationItem.OperationType.CLOSE_AC)
                .setUserId(userId)
                .setAcTemp(acState.getTemp())
                .setAcWindSpeed(acState.getWindSpeed())
                .setCreateTime(LocalDateTime.now())
                .build();
        acOperationRepository.createOperationItem(operationItem);
        System.out.println("关闭空调"+acId);

    }

    public void openAc(int acId,int userId)throws Exception {
        acRepository.openAc(acId);
        Ac acState = acRepository.getAcState(acId);
        OperationItem operationItem=new OperationItem.Builder()
                .setAcId(acId)
                .setType(OperationItem.OperationType.OPEN_AC)
                .setUserId(userId)
                .setAcWindSpeed(acState.getWindSpeed())
                .setAcTemp(acState.getTemp())
                .setCreateTime(LocalDateTime.now())
                .build();
        acOperationRepository.createOperationItem(operationItem);
        System.out.println("打开空调"+acId);
    }

    public List<OperationItem> getAcDetailTableByUserId(int userId)throws Exception {
        List<OperationItem> acOperationTableByAcId = acOperationRepository.getAcOperationTableByUserId(userId);
        return acOperationTableByAcId;
    }

    public List<OperationItem> getAcDetailTableByAcId(int acId) throws Exception{
        List<OperationItem> acOperationTableByAcId = acOperationRepository.getAcOperationTableByAcId(acId);
        return acOperationTableByAcId;
    }

    public Ac getAcState (int acId) throws Exception {
        return acRepository.getAcState(acId);
    }
    public Ac getAcState (String room) throws Exception{return acRepository.getAcState(room);}
    public List<Ac> getAllAcState() throws Exception {
        return acRepository.getAllAcState();
    }

    public void createOperationItem(OperationItem operationItem) throws Exception{
        acOperationRepository.createOperationItem(operationItem);
    }
    public void deleteAc(int acId) throws Exception{
        acRepository.deleteAc(acId);
    }
    public int getRoomTemp(int acId) throws Exception{ return acRepository.getRoomTemp(acId);}
    public void changeRoomTemp(int acId,float newTemp) throws Exception{ acRepository.changeRoomTemp(acId,newTemp);
      }


}
