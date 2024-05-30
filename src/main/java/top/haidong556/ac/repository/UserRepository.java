package top.haidong556.ac.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.ac.entity.role.*;
import org.springframework.stereotype.Repository;
import top.haidong556.ac.mapper.AdminMapper;
import top.haidong556.ac.mapper.ManagerMapper;
import top.haidong556.ac.mapper.UserMapper;
import top.haidong556.ac.mapper.WaiterMapper;
import top.haidong556.ac.util.MysqlFactory;

import java.util.List;

@Repository
public class UserRepository {

    public void addUser(User user) {
        try (SqlSession session = MysqlFactory.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.createUser(user);
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

    public User getUser(int userId) {
        try (SqlSession session = MysqlFactory.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get user by ID", e);
        }
    }

    public User getUser(String username) {
        try (SqlSession session = MysqlFactory.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get user by username", e);
        }
    }

    public List<User> getAllUser() {
        try (SqlSession session = MysqlFactory.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get all users", e);
        }
    }
    public Manager getManager(String username){
        try (SqlSession session = MysqlFactory.getSession()) {
            ManagerMapper mapper = session.getMapper(ManagerMapper.class);
            return mapper.getManagerByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get user by username", e);
        }
    }
    public Waiter getWaiter(String username){
        try (SqlSession session = MysqlFactory.getSession()) {
            WaiterMapper mapper = session.getMapper(WaiterMapper.class);
            return mapper.getWaiterByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get user by username", e);
        }
    }
    public Admin getAdmin(String username){
        try (SqlSession session = MysqlFactory.getSession()) {
            AdminMapper mapper = session.getMapper(AdminMapper.class);
            return mapper.getAdminByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get user by username", e);
        }
    }

}
