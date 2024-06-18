package top.haidong556.ac.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.exception.DataBaseException;
import top.haidong556.ac.mapper.AcOperationMapper;
import top.haidong556.ac.util.MysqlFactory;

import java.util.List;

@Repository
public class AcOperationRepository {

    public List<OperationItem> getAcOperationTableByUserId(int userId) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            List<OperationItem> acOperationTableByUserId = mapper.getAcOperationItemsByUserId(userId);
            session.commit();
            return acOperationTableByUserId;
        }catch (Exception e){
            throw new DataBaseException("获取操作表失败",e);
        }
    }

    public List<OperationItem> getAcOperationTableByAcId(int acId) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            List<OperationItem> acOperationTableByAcId = mapper.getAcOperationItemsByAcId(acId);
            session.commit();
            return acOperationTableByAcId;
        }catch (Exception e){
            throw new DataBaseException("获取操作表失败",e);
        }
    }

    public void createOperationItem(OperationItem operationItem) throws DataBaseException{
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            mapper.createOperationItem(operationItem);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("创建操作条目失败",e);
        }
    }

    public void deleteOperationItem(int opId)  throws DataBaseException{
        try (SqlSession session = MysqlFactory.getSession()) {
            AcOperationMapper mapper = session.getMapper(AcOperationMapper.class);
            mapper.deleteOperationItem(opId);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("删除操作条目失败",e);
        }
    }

}
