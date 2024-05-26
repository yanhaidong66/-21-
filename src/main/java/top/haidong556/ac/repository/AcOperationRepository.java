package top.haidong556.ac.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.mapper.AcOperationMapper;
import top.haidong556.ac.util.MysqlFactory;

import java.util.List;

@Repository
public class AcOperationRepository {

    public List<OperationItem> getAcOperationTableByUserId(int userId) {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            List<OperationItem> acOperationTableByUserId = mapper.getAcOperationItemsByUserId(userId);
            session.commit();
            return acOperationTableByUserId;
        } catch (Exception e) {
            // Handle exception if needed
            e.printStackTrace();
            throw new RuntimeException("Failed to get AC operation table by user ID", e);
        }
    }

    public List<OperationItem> getAcOperationTableByAcId(int acId) {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            List<OperationItem> acOperationTableByAcId = mapper.getAcOperationItemsByAcId(acId);
            session.commit();
            return acOperationTableByAcId;
        } catch (Exception e) {
            // Handle exception if needed
            e.printStackTrace();
            throw new RuntimeException("Failed to get AC operation table by AC ID", e);
        }
    }

    public void createOperationItem(OperationItem operationItem) {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            mapper.createOperationItem(operationItem);
            session.commit();
        } catch (Exception e) {
            // Handle exception if needed
            e.printStackTrace();
            throw new RuntimeException("Failed to create AC operation item", e);
        }
    }
}
