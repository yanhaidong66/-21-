package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.*;
import top.haidong556.ac.entity.role.People;
import top.haidong556.ac.entity.role.User;
import top.haidong556.ac.entity.role.Waiter;
import top.haidong556.ac.mapper.handler.RoleTypeHandler;

import java.util.List;
@Mapper
public interface WaiterMapper {
    @Insert("INSERT INTO t_user ( user_username, user_password, user_role) VALUES ( #{username}, #{password}, #{roleType})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void createWaiter(Waiter waiter);

    @Delete("DELETE FROM t_user WHERE user_id = #{userId}")
    void deleteWaiter(int userId);

    @Select("SELECT user_id AS userId, user_username AS username, user_password AS password, user_role AS roleType FROM t_user WHERE user_id = #{userId}")
    @Results({
            @Result(property = "roleType", column = "roleType", javaType = People.RoleType.class, typeHandler = RoleTypeHandler.class)
    })
    Waiter getWaiterById(int userId);
    @Select("SELECT user_id AS userId, user_username AS username, user_password as password, user_role AS roleType FROM t_user WHERE user_username=#{username} AND user_role = 'WAITER'")
    @Results({
            @Result(property = "roleType", column = "roleType",javaType = People.RoleType.class,typeHandler = RoleTypeHandler.class)
    })
    Waiter getWaiterByUsername(String username);

    @Select("SELECT user_id AS userId, user_username AS username, user_password AS password, user_role AS roleType FROM t_user where user_role=3")
    @Results({
            @Result(property = "roleType", column = "roleType", javaType = People.RoleType.class, typeHandler = RoleTypeHandler.class)
    })
    List<User> getAllWaiter();
}
