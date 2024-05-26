package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.*;
import top.haidong556.ac.entity.ac.Ac;

@Mapper
public interface AcMapper {

    @Insert("INSERT INTO t_ac (ac_room, ac_state, ac_cost_per_hour, ac_wind_speed, ac_temp) VALUES (#{room}, #{acState}, #{costPerHour}, #{windSpeed}, #{temp})")
    @Options(useGeneratedKeys = true, keyProperty = "acId")
    void addAc(Ac ac);
    @Select("SELECT ac_state AS acState, ac_id AS acId, ac_wind_speed AS windSpeed, ac_temp AS temp, ac_room AS room, ac_cost_per_hour AS costPerHour FROM t_ac WHERE ac_id = #{acId}")
    Ac getAcState(int acId);

    @Update("UPDATE t_ac SET ac_state = 0 WHERE ac_id = #{acId}")
    void closeAc(int acId);

    @Update("UPDATE t_ac SET ac_state = 1 WHERE ac_id = #{acId}")
    void openAc(int acId);

    @Update("UPDATE t_ac SET ac_wind_speed = #{newWindSpeed} WHERE ac_id = #{acId}")
    void changeAcWindSpeed(@Param("acId") int acId, @Param("newWindSpeed") int newWindSpeed);

    @Update("UPDATE t_ac SET ac_temp = #{newTemp} WHERE ac_id = #{acId}")
    void changeAcTemp(@Param("acId") int acId, @Param("newTemp") int newTemp);
}



