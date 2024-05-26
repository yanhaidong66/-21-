package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.haidong556.ac.entity.operationDetail.OperationTable;
import top.haidong556.ac.repository.AcOperationRepository;
import top.haidong556.ac.service.AcService;

@Controller
public class AcOperationController {
    private AcService acService;
    @Autowired
    private void setAcService(){}
    @GetMapping("/getAcOperationTableByUserId/{userId}")
    public void getAcOperationTableByUserId(@PathVariable("userId") int userId){
        OperationTable acDetailTableByUserId = acService.getAcDetailTableByUserId(userId);
    }
    @GetMapping("/getAcOperationTableByAcId/{acId}")
    public void getAcOperationTableByAcId(@PathVariable("acId") int acId){
        OperationTable acDetailTableByAcId = acService.getAcDetailTableByAcId(acId);

    }

}
