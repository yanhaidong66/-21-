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

    public void addUser(User user) throws Exception {
        SqlSession session = MysqlFactory.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.createUser(user);
        session.commit();
    }

    public void deleteUser(int userId) throws Exception {
        SqlSession session = MysqlFactory.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.deleteUser(userId);
        session.commit();
    }

    public User getUser(int userId) throws Exception {
        SqlSession session = MysqlFactory.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        return mapper.getUserById(userId);
    }

    public User getUser(String username) throws Exception {
        SqlSession session = MysqlFactory.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        return mapper.getUserByUsername(username);
    }

    public List<User> getAllUser() throws Exception {
        SqlSession session = MysqlFactory.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        return mapper.getAllUser();
    }

    public Manager getManager(String username) throws Exception {
        SqlSession session = MysqlFactory.getSession();
        ManagerMapper mapper = session.getMapper(ManagerMapper.class);
        return mapper.getManagerByUsername(username);
    }

    public Waiter getWaiter(String username) throws Exception {
        SqlSession session = MysqlFactory.getSession();
        WaiterMapper mapper = session.getMapper(WaiterMapper.class);
        return mapper.getWaiterByUsername(username);
    }

    public Admin getAdmin(String username) throws Exception {
        SqlSession session = MysqlFactory.getSession();
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        return mapper.getAdminByUsername(username);
    }

}
