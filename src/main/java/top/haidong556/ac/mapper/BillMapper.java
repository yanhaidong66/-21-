package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.*;
import top.haidong556.ac.entity.bill.BillItem;
import top.haidong556.ac.entity.bill.BillItem.BillState;
import top.haidong556.ac.mapper.handler.BillStateHandler;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BillMapper {

    @Select("SELECT bill_id AS billId, bill_state AS state, bill_create_time AS datetime, bill_user_id AS userId, bill_ac_id AS acId FROM t_bill WHERE bill_create_time BETWEEN #{createTime} AND #{endTime}")
    @Results({
            @Result(property = "state", column = "bill_state", javaType = BillState.class, typeHandler = BillStateHandler.class)
    })
    List<BillItem> getBillItemByTime(@Param("createTime") LocalDateTime createTime, @Param("endTime") LocalDateTime endTime);

    @Insert("INSERT INTO t_bill (bill_state, bill_create_time, bill_user_id, bill_ac_id) VALUES (#{state}, #{datetime}, #{userId}, #{acId})")
    @Options(useGeneratedKeys = true, keyProperty = "billId")
    void createBillItem(BillItem billItem);

    @Delete("DELETE FROM t_bill WHERE bill_id = #{billId}")
    void deleteBillItem(int billId);
}