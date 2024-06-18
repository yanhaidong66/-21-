package top.haidong556.ac.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.ac.entity.ac.Ac;
import org.springframework.stereotype.Repository;
import top.haidong556.ac.exception.DataBaseException;
import top.haidong556.ac.mapper.AcMapper;
import top.haidong556.ac.util.MysqlFactory;

import java.util.List;

@Repository
public class AcRepository {

    public void addAc(Ac ac) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.addAc(ac);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("添加空调失败",e);
        }
    }

    public Ac getAcState(String acRoom) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            return mapper.getAcStateByRoom(acRoom);
        }catch (Exception e){
            throw new DataBaseException("获取空调状态失败",e);
        }
    }

    public Ac getAcState(int acId) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            return mapper.getAcStateById(acId);
        }catch (Exception e){
            throw new DataBaseException("获取空调状态失败",e);
        }
    }

    public int getRoomTemp(int acId) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            return mapper.getRoomTemp(acId);
        }catch (Exception e){
            throw new DataBaseException("获取空调状态失败",e);
        }
    }

    public List<Ac> getAllAcState() throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            return mapper.getAllAcState();
        }catch (Exception e){
            throw new DataBaseException("获取空调状态失败",e);
        }
    }

    public void closeAc(int acId) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.closeAc(acId);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("关闭空调失败",e);
        }
    }

    public void openAc(int acId) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.openAc(acId);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("开启空调失败",e);
        }
    }

    public void changeAcWindSpeed(int acId, int newWindSpeed) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.changeAcWindSpeed(acId, newWindSpeed);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("修改空调风速失败",e);
        }
    }

    public void changeAcTemp(int acId, int newTemp) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.changeAcTemp(acId, newTemp);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("修改空调温度失败",e);
        }
    }

    public void changeRoomTemp(int acId, float newTemp) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.changeRoomTemp(newTemp, acId);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("修改室温失败",e);
        }
    }

    public void deleteAc(int acId) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.deleteAcById(acId);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("删除空调失败",e);
        }
    }

    public void deleteAc(String acRoom) throws DataBaseException {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.deleteAcByRoom(acRoom);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("删除空调失败",e);
        }
    }

}
