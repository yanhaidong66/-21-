package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.*;
import top.haidong556.ac.entity.role.People;
import top.haidong556.ac.entity.role.People.RoleType;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.mapper.handler.RoleTypeHandler;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO t_user (user_ac_id, user_username, user_password, user_role) VALUES (#{userAcId}, #{username}, #{password}, #{roleType})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void createUser(User user);

    @Delete("DELETE FROM t_user WHERE user_id = #{userId}")
    void deleteUser(int userId);

    @Select("SELECT user_id AS userId, user_ac_id AS userAcId, user_username AS username, user_password AS password, user_role AS roleType FROM t_user WHERE user_id = #{userId}")
    @Results({
            @Result(property = "roleType", column = "roleType", javaType = RoleType.class, typeHandler = RoleTypeHandler.class)
    })
    People getUser(int userId);

    @Select("SELECT user_id AS userId, user_ac_id AS userAcId, user_username AS username, user_password AS password, user_role AS roleType FROM t_user WHERE user_username = #{username}")
    @Results({
            @Result(property = "roleType", column = "roleType", javaType = RoleType.class, typeHandler = RoleTypeHandler.class)
    })
    User getUser(String username);

    @Select("SELECT user_id AS userId, user_ac_id AS userAcId, user_username AS username, user_password AS password, user_role AS roleType FROM t_user")
    @Results({
            @Result(property = "roleType", column = "roleType", javaType = RoleType.class, typeHandler = RoleTypeHandler.class)
    })
    List<People> getAllUser();
}

