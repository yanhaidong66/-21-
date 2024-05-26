package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.*;
import top.haidong556.ac.entity.operationDetail.OperationItem;
import top.haidong556.ac.mapper.handler.OperationTypeHandler;

import java.util.List;

@Mapper
public interface AcOperationMapper {

    @Select("SELECT operation_id AS operationId, user_id AS userId, operation_type AS type, create_time AS createTime, ac_id AS acId FROM t_ac_operation WHERE user_id = #{userId}")
    @Results({
            @Result(property = "type", column = "operation_type", javaType = OperationItem.OperationType.class, typeHandler = OperationTypeHandler.class)
    })
    List<OperationItem> getAcOperationTableByUserId(int userId);

    @Select("SELECT operation_id AS operationId, user_id AS userId, operation_type AS type, create_time AS createTime, ac_id AS acId FROM t_ac_operation WHERE ac_id = #{acId}")
    @Results({
            @Result(property = "type", column = "operation_type", javaType = OperationItem.OperationType.class, typeHandler = OperationTypeHandler.class)
    })
    List<OperationItem> getAcOperationTableByAcId(int acId);
}
