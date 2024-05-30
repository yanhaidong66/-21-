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

import java.util.List;

@Service
public class AcService {
    private final AcRepository acRepository;
    private final AcOperationRepository acOperationRepository;
    private MyUserDetailsServiceImpl userDetailsService;

    @Autowired
    public AcService(AcRepository acRepository, AcOperationRepository acOperationRepository,MyUserDetailsServiceImpl userDetailsService) {
        this.acRepository = acRepository;
        this.acOperationRepository = acOperationRepository;
        this.userDetailsService=userDetailsService;
    }

    public void addAc(Ac ac) {
        acRepository.addAc(ac);
        System.out.println("添加空调设备："+ac);
    }

    public void changeAcTemp(int acId, int newTemp) {
        acRepository.changeAcTemp(acId, newTemp);
        System.out.println("修改空调id="+acId+"温度为"+newTemp);
    }

    public void changeAcWindSpeed(int acId, int newWindSpeed) {
        acRepository.changeAcWindSpeed(acId, newWindSpeed);
        System.out.println("修改空调"+acId+"风速为："+newWindSpeed);
    }

    public void closeAc(int acId) {
        acRepository.closeAc(acId);
        System.out.println("关闭空调"+acId);

    }

    public void openAc(int acId) {
        acRepository.openAc(acId);
        System.out.println("打开空调"+acId);
    }

    public List<OperationItem> getAcDetailTableByUserId(int userId) {
        List<OperationItem> acOperationTableByAcId = acOperationRepository.getAcOperationTableByUserId(userId);
        return acOperationTableByAcId;
    }

    public List<OperationItem> getAcDetailTableByAcId(int acId) {
        List<OperationItem> acOperationTableByAcId = acOperationRepository.getAcOperationTableByAcId(acId);

        return acOperationTableByAcId;
    }

    public Ac getAcState(int acId) {
        return acRepository.getAcState(acId);
    }
    public List<Ac> getAllAcState() {
        return acRepository.getAllAcState();
    }

    public void createOperationItem(OperationItem operationItem){
        acOperationRepository.createOperationItem(operationItem);
    }


}
