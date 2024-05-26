package top.haidong556.ac.mapper;

import top.haidong556.ac.entity.role.People;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public void addUser(People people);
    public void deleteUser();
    public People getUser(int userId);
    public People getUser(String username);
    public List<People> getAllUser();
}
