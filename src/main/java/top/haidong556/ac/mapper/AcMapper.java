package top.haidong556.ac.mapper;

import org.apache.ibatis.annotations.Insert;
import top.haidong556.ac.entity.ac.Ac;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AcMapper {
    @Insert("insert into ")
    public void addAc(Ac ac);
    public Ac getAcState(int acId);
    public void closeAc(int acId);
    public void openAc(int acId);
    public void changeAcWindSpeed(int acId,int newWindSpeed);
    public void changeAcTemp(int acId,int newTemp);
}
