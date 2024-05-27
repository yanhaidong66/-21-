package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.*;
import top.haidong556.ac.entity.role.*;
import top.haidong556.ac.mapper.handler.RoleTypeHandler;

import java.util.List;
@Mapper
public interface AdminMapper {
    @Insert("INSERT INTO t_user ( user_username, user_password, user_role) VALUES ( #{username}, #{password}, #{roleType})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void createAdmin(Admin waiter);

    @Delete("DELETE FROM t_user WHERE user_id = #{userId}")
    void deleteAdmin(int userId);

    @Select("SELECT user_id AS userId, user_username AS username, user_password AS password, user_role AS roleType FROM t_user WHERE user_id = #{userId}")
    @Results({
            @Result(property = "roleType", column = "roleType", javaType = People.RoleType.class, typeHandler = RoleTypeHandler.class)
    })
    Admin getAdminById(int userId);
    @Select("SELECT user_id AS userId, user_username AS username, user_password as password, user_role AS roleType FROM t_user WHERE user_username=#{username} AND user_role='ADMIN'")
    @Results({
            @Result(property = "roleType", column = "roleType",javaType = People.RoleType.class,typeHandler = RoleTypeHandler.class)
    })
    Admin getAdminByUsername(String username);

    @Select("SELECT user_id AS userId, user_username AS username, user_password AS password, user_role AS roleType FROM t_user where user_role=1")
    @Results({
            @Result(property = "roleType", column = "roleType", javaType = People.RoleType.class, typeHandler = RoleTypeHandler.class)
    })
    List<Admin> getAllAdmin();
}