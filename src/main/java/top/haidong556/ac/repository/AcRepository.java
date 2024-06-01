package top.haidong556.ac.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.ac.entity.ac.Ac;
import org.springframework.stereotype.Repository;
import top.haidong556.ac.mapper.AcMapper;
import top.haidong556.ac.util.MysqlFactory;

import java.util.List;

@Repository
public class AcRepository {

    public void addAc(Ac ac) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.addAc(ac);
            session.commit();
        }
    }

    public Ac getAcState(String acRoom) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            return mapper.getAcStateByRoom(acRoom);
        }
    }

    public Ac getAcState(int acId) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            return mapper.getAcStateById(acId);
        }
    }

    public int getRoomTemp(int acId) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            return mapper.getRoomTemp(acId);
        }
    }

    public List<Ac> getAllAcState() throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            return mapper.getAllAcState();
        }
    }

    public void closeAc(int acId) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.closeAc(acId);
            session.commit();
        }
    }

    public void openAc(int acId) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.openAc(acId);
            session.commit();
        }
    }

    public void changeAcWindSpeed(int acId, int newWindSpeed) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.changeAcWindSpeed(acId, newWindSpeed);
            session.commit();
        }
    }

    public void changeAcTemp(int acId, int newTemp) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.changeAcTemp(acId, newTemp);
            session.commit();
        }
    }

    public void changeRoomTemp(int acId, float newTemp) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.changeRoomTemp(newTemp, acId);
            session.commit();
        }
    }

    public void deleteAc(int acId) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.deleteAcById(acId);
            session.commit();
        }
    }

    public void deleteAc(String acRoom) throws Exception {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.deleteAcByRoom(acRoom);
            session.commit();
        }
    }

}
