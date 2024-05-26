package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AcOperationMapper {
    public void getAcOperationTableByUserId(int userId);
    public void getAcOperationTableByAcId(int acId);
}
