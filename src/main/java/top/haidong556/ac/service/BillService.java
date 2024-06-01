package top.haidong556.ac.service;

import top.haidong556.ac.entity.bill.BillItem;
import top.haidong556.ac.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillService {
    private BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository){
        this.billRepository=billRepository;
    }
    public List<BillItem> getBillItemByTime(LocalDateTime startTime, LocalDateTime endTime) {
        List<BillItem> billItemByTime = billRepository.getBillItemByTime(startTime, endTime);
        return billItemByTime;
    }

    // Create a new bill item
    public void createBillItem(BillItem billItem) {
        billRepository.createBillItem(billItem);

    }

    // Delete a bill item by ID
    public void deleteBillItem(int billItemId) {
        billRepository.deleteBillItem(billItemId);
    }


}
