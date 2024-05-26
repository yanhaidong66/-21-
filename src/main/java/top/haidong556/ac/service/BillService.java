package top.haidong556.ac.service;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.ac.entity.bill.BillItem;
import top.haidong556.ac.entity.bill.BillTable;
import top.haidong556.ac.mapper.BillMapper;
import top.haidong556.ac.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.haidong556.ac.util.MysqlFactory;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillService {
    private BillRepository billRepository;
    @Autowired
    private void setBillRepository(){}


    public BillTable getBillItemByTime(LocalDateTime createTime, LocalDateTime endTime) {
        List<BillItem> billItemByTime = billRepository.getBillItemByTime(createTime, endTime);
        BillTable billTable=new BillTable();
        return
    }

    // Create a new bill item
    public void createBillItem(BillItem billItem) {

    }

    // Delete a bill item by ID
    public void deleteBillItem(int billItemId) {

    }



}
