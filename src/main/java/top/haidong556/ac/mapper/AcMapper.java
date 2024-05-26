package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.*;
import top.haidong556.ac.entity.ac.Ac;

@Mapper
public interface AcMapper {

    @Insert("INSERT INTO t_ac (ac_room, ac_state, ac_user_id, ac_cost) VALUES (#{room}, #{acState}, #{acUserId}, #{costPerHour})")
    @Options(useGeneratedKeys = true, keyProperty = "acId")
    void addAc(Ac ac);

    @Select("SELECT ac_state as acState, ac_id as acId, 0 as windSpeed, 0 as temp, ac_room as room FROM t_ac WHERE ac_id = #{acId}")
    Ac getAcState(int acId);

    @Update("UPDATE t_ac SET ac_state = 0 WHERE ac_id = #{acId}")
    void closeAc(int acId);

    @Update("UPDATE t_ac SET ac_state = 1 WHERE ac_id = #{acId}")
    void openAc(int acId);

    @Update("UPDATE t_ac SET wind_speed = #{newWindSpeed} WHERE ac_id = #{acId}")
    void changeAcWindSpeed(@Param("acId") int acId, @Param("newWindSpeed") int newWindSpeed);

    @Update("UPDATE t_ac SET temp = #{newTemp} WHERE ac_id = #{acId}")
    void changeAcTemp(@Param("acId") int acId, @Param("newTemp") int newTemp);
}

