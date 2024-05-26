package top.haidong556.ac.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.ac.entity.role.People;
import org.springframework.stereotype.Repository;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.mapper.UserMapper;
import top.haidong556.ac.util.MysqlFactory;

import java.util.List;

@Repository
public class UserRepository {

    public void addUser(People people) {
        try (SqlSession session = MysqlFactory.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.createUser((User) people);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add user", e);
        }
    }

    public void deleteUser(int userId) {
        try (SqlSession session = MysqlFactory.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.deleteUser(userId);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    public People getUser(int userId) {
        try (SqlSession session = MysqlFactory.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get user by ID", e);
        }
    }

    public People getUser(String username) {
        try (SqlSession session = MysqlFactory.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUser(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get user by username", e);
        }
    }

    public List<People> getAllUser() {
        try (SqlSession session = MysqlFactory.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get all users", e);
        }
    }
}
