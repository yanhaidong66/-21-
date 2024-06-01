package top.haidong556.ac.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.ac.entity.bill.BillItem;
import org.springframework.stereotype.Repository;
import top.haidong556.ac.mapper.BillMapper;
import top.haidong556.ac.util.MysqlFactory;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BillRepository {

    // Get bill items within a specific time range
    public List<BillItem> getBillItemByTime(LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            BillMapper mapper = session.getMapper(BillMapper.class);
            return mapper.getBillItemByTime(startTime, endTime);
        }
    }

    // Create a new bill item
    public void createBillItem(BillItem billItem) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            BillMapper mapper = session.getMapper(BillMapper.class);
            mapper.createBillItem(billItem);
            session.commit();
        }
    }

    // Delete a bill item by ID
    public void deleteBillItem(int billItemId) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            BillMapper mapper = session.getMapper(BillMapper.class);
            mapper.deleteBillItem(billItemId);
            session.commit();
        }
    }

}
