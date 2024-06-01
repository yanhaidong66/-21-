package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.*;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.mapper.handler.OperationTypeHandler;

import java.util.List;

@Mapper
public interface AcOperationMapper {


    @Select("SELECT op_id AS operationId, op_ac_temp AS acTemp,op_ac_wind_speed AS acWindSpeed, op_user_id AS userId, op_type AS type, op_create_time AS createTime, op_ac_id AS acId FROM t_ac_operation WHERE op_user_id = #{userId} order by createTime")
    @Results({
            @Result(property = "type", column = "type", javaType = OperationItem.OperationType.class, typeHandler = OperationTypeHandler.class)
    })
    List<OperationItem> getAcOperationItemsByUserId(int userId);

    @Select("SELECT op_id AS operationId, op_ac_temp AS acTemp,op_ac_wind_speed AS acWindSpeed, op_user_id AS userId, op_type AS type, op_create_time AS createTime, op_ac_id AS acId FROM t_ac_operation WHERE op_ac_id = #{acId}")
    @Results({
            @Result(property = "type", column = "ype", javaType = OperationItem.OperationType.class, typeHandler = OperationTypeHandler.class)
    })
    List<OperationItem> getAcOperationItemsByAcId(int acId);

    @Insert("INSERT INTO t_ac_operation(op_user_id, op_type, op_create_time, op_ac_id, op_ac_temp,op_ac_wind_speed ) VALUES (#{userId}, #{type}, #{createTime}, #{acId},#{acTemp},#{acWindSpeed})")
    @Options(useGeneratedKeys = true, keyProperty = "operationId")
    void createOperationItem(OperationItem operationItem);
    @Delete("DELETE FROM t_ac_operation WHERE op_id = #{operationId}")
    void deleteOperationItem(int operationId);
}