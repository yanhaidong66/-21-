package top.haidong556.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.haidong556.ac.entity.operationDetail.OperationTable;
import top.haidong556.ac.repository.AcOperationRepository;
import top.haidong556.ac.service.AcService;

@Controller
@RequestMapping("acOperation")
public class AcOperationController {
    private AcService acService;
    @Autowired
    public AcOperationController(AcService acService) {
        this.acService = acService;
    }

    @GetMapping("/byUserId/{userId}")
    public void getAcOperationTableByUserId(@PathVariable("userId") int userId){
        OperationTable acDetailTableByUserId = acService.getAcDetailTableByUserId(userId);
    }
    @GetMapping("/byAcId/{acId}")
    public void getAcOperationTableByAcId(@PathVariable("acId") int acId){
        OperationTable acDetailTableByAcId = acService.getAcDetailTableByAcId(acId);

    }

}
