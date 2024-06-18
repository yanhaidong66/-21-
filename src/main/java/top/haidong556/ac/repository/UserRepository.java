package top.haidong556.ac.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.ac.entity.role.*;
import org.springframework.stereotype.Repository;
import top.haidong556.ac.exception.DataBaseException;
import top.haidong556.ac.mapper.AdminMapper;
import top.haidong556.ac.mapper.ManagerMapper;
import top.haidong556.ac.mapper.UserMapper;
import top.haidong556.ac.mapper.WaiterMapper;
import top.haidong556.ac.util.MysqlFactory;

import java.util.List;

@Repository
public class UserRepository {

    public void addUser(User user) throws DataBaseException{
        try(SqlSession session = MysqlFactory.getSession();) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.createUser(user);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("添加用户失败",e);
        }
    }

    public void deleteUser(int userId) throws DataBaseException {
        try(SqlSession session = MysqlFactory.getSession();) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.deleteUser(userId);
            session.commit();
        }catch (Exception e){
            throw new DataBaseException("删除用户失败",e);
        }

    }

    public User getUser(int userId) throws DataBaseException {
        try(SqlSession session = MysqlFactory.getSession();) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUserById(userId);
        }catch (Exception e){
            throw new DataBaseException("获取用户失败",e);
        }

    }

    public User getUser(String username) throws DataBaseException {
        try(SqlSession session = MysqlFactory.getSession();) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUserByUsername(username);
        }catch (Exception e){
            throw new DataBaseException("获取用户失败",e);
        }
    }

    public List<User> getAllUser() throws DataBaseException{
        try(SqlSession session = MysqlFactory.getSession();) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getAllUser();
        }catch (Exception e){
            throw new DataBaseException("获取用户失败",e);
        }
    }

    public Manager getManager(String username) throws DataBaseException {
        try(SqlSession session = MysqlFactory.getSession();) {
            ManagerMapper mapper = session.getMapper(ManagerMapper.class);
            return mapper.getManagerByUsername(username);
        }catch (Exception e){
            throw new DataBaseException("获取用户失败",e);
        }
    }

    public Waiter getWaiter(String username) throws DataBaseException {
        try(SqlSession session = MysqlFactory.getSession();) {
            WaiterMapper mapper = session.getMapper(WaiterMapper.class);
            return mapper.getWaiterByUsername(username);
        }catch (Exception e){
            throw new DataBaseException("获取用户失败",e);
        }
    }

    public Admin getAdmin(String username) throws DataBaseException {
        try(SqlSession session = MysqlFactory.getSession();) {
            AdminMapper mapper = session.getMapper(AdminMapper.class);
            return mapper.getAdminByUsername(username);
        }catch (Exception e){
            throw new DataBaseException("获取用户失败",e);
        }
    }

}
