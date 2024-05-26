package top.haidong556.ac.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.ac.entity.ac.Ac;
import org.springframework.stereotype.Repository;
import top.haidong556.ac.mapper.AcMapper;
import top.haidong556.ac.util.MysqlFactory;

@Repository
public class AcRepository {

    public void addAc(Ac ac) {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.addAc(ac);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add AC", e);
        }
    }

    public Ac getAcState(int acId) {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            return mapper.getAcState(acId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get AC state", e);
        }
    }

    public void closeAc(int acId) {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.closeAc(acId);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to close AC", e);
        }
    }

    public void openAc(int acId) {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.openAc(acId);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to open AC", e);
        }
    }

    public void changeAcWindSpeed(int acId, int newWindSpeed) {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.changeAcWindSpeed(acId, newWindSpeed);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to change AC wind speed", e);
        }
    }

    public void changeAcTemp(int acId, int newTemp) {
        try (SqlSession session = MysqlFactory.getSession()) {
            AcMapper mapper = session.getMapper(AcMapper.class);
            mapper.changeAcTemp(acId, newTemp);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to change AC temperature", e);
        }
    }
}
