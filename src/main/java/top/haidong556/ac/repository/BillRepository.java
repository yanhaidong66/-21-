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
    public List<BillItem> getBillItemByTime(LocalDateTime createTime, LocalDateTime endTime) {
        try (SqlSession session = MysqlFactory.getSession()) {
            BillMapper mapper = session.getMapper(BillMapper.class);
            return mapper.getBillItemByTime(createTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve bill items", e);
        }
    }

    // Create a new bill item
    public void createBillItem(BillItem billItem) {
        try (SqlSession session = MysqlFactory.getSession()) {
            BillMapper mapper = session.getMapper(BillMapper.class);
            mapper.createBillItem(billItem);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create bill item", e);
        }
    }

    // Delete a bill item by ID
    public void deleteBillItem(int billItemId) {
        try (SqlSession session = MysqlFactory.getSession()) {
            BillMapper mapper = session.getMapper(BillMapper.class);
            mapper.deleteBillItem(billItemId);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete bill item", e);
        }
    }
}
