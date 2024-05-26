package top.haidong556.ac.repository;

import top.haidong556.ac.entity.bill.BillItem;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BillRepository {
    public List<BillItem> getBillItemByTime(LocalDateTime createTime,LocalDateTime endTime){

    }
    public void createBillItem(BillItem bIllItem){

    }
    public void deleteBillItem(int billItemId){

    }

}
