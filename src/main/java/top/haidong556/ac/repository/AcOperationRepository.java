package top.haidong556.ac.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.mapper.AcOperationMapper;
import top.haidong556.ac.util.MysqlFactory;

import java.util.List;

@Repository
public class AcOperationRepository {

    public List<OperationItem> getAcOperationTableByUserId(int userId) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            List<OperationItem> acOperationTableByUserId = mapper.getAcOperationItemsByUserId(userId);
            session.commit();
            return acOperationTableByUserId;
        }
    }

    public List<OperationItem> getAcOperationTableByAcId(int acId) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            List<OperationItem> acOperationTableByAcId = mapper.getAcOperationItemsByAcId(acId);
            session.commit();
            return acOperationTableByAcId;
        }
    }

    public void createOperationItem(OperationItem operationItem) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            mapper.createOperationItem(operationItem);
            session.commit();
        }
    }

    public void deleteOperationItem(int opId) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            mapper.deleteOperationItem(opId);
            session.commit();
        }
    }

}
