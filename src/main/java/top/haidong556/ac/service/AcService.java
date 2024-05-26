package top.haidong556.ac.service;

import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.entity.operationDetail.OperationTable;
import top.haidong556.ac.repository.AcOperationRepository;
import top.haidong556.ac.repository.AcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addAc(Ac ac) {
        acRepository.addAc(ac);
    }

    public void changeAcTemp(int acId, int newTemp) {
        acRepository.changeAcTemp(acId, newTemp);
    }

    public void changeAcWindSpeed(int acId, int newWindSpeed) {
        acRepository.changeAcWindSpeed(acId, newWindSpeed);
    }

    public void closeAc(int acId) {
        acRepository.closeAc(acId);
    }

    public void openAc(int acId) {
        acRepository.openAc(acId);
    }

    public OperationTable getAcDetailTableByUserId(int userId) {
        List<OperationItem> acOperationTableByAcId = acOperationRepository.getAcOperationTableByUserId(userId);
        OperationTable table = new OperationTable.Builder()
                .setOperationItems(acOperationTableByAcId)
                .build();
        return table;
    }

    public OperationTable getAcDetailTableByAcId(int acId) {
        List<OperationItem> acOperationTableByAcId = acOperationRepository.getAcOperationTableByAcId(acId);
        OperationTable table = new OperationTable.Builder()
                .setOperationItems(acOperationTableByAcId)
                .build();
        return table;
    }

    public Ac getAcState(int acId) {
        return acRepository.getAcState(acId);
    }
}
