package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.*;
import top.haidong556.ac.entity.role.People;
import top.haidong556.ac.entity.role.People.RoleType;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.mapper.handler.RoleTypeHandler;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO t_user (user_ac_id, user_username, user_password, user_role) VALUES (#{acId}, #{username}, #{password}, #{roleType})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void createUser(User user);

    @Delete("DELETE FROM t_user WHERE user_id = #{userId} AND user_role='USER'")
    void deleteUser(int userId);

    @Select("SELECT user_id AS userId, user_ac_id AS acId, user_username AS username, user_password AS password, user_role AS roleType FROM t_user WHERE user_ac_id = #{acId} AND user_role = 'USER'")
    @Results({
            @Result(property = "roleType", column = "roleType", javaType = RoleType.class, typeHandler = RoleTypeHandler.class)
    })
    List<User> getUserByAcId(int acId);

    @Select("SELECT user_id AS userId, user_ac_id AS acId, user_username AS username, user_password AS password, user_role AS roleType FROM t_user WHERE user_id = #{userId} AND user_role = 'USER'")
    @Results({
            @Result(property = "roleType", column = "roleType", javaType = RoleType.class, typeHandler = RoleTypeHandler.class)
    })
    User getUserById(int userId);
    @Select("SELECT user_id AS userId, user_ac_id AS acId, user_username AS username, user_password as password, user_role AS roleType FROM t_user WHERE user_username=#{username} and user_role = 'USER'")
    @Results({
            @Result(property = "roleType", column = "roleType",javaType =RoleType.class,typeHandler = RoleTypeHandler.class)
    })
    User getUserByUsername(String username);

    @Select("SELECT user_id AS userId, user_ac_id AS acId, user_username AS username, user_password AS password, user_role AS roleType FROM t_user WHERE user_role='USER'")
    @Results({
            @Result(property = "roleType", column = "roleType", javaType = RoleType.class, typeHandler = RoleTypeHandler.class)
    })
    List<User> getAllUser();
}

