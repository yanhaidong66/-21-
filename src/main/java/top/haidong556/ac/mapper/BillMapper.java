package top.haidong556.ac.mapper;

import top.haidong556.ac.entity.bill.BIllItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillMapper {
    public List<BIllItem> getBillItemByTime(createTime, endTime);
    public void createBillItem(BIllItem bIllItem);
    public void deleteBillItem(int billItemId);
}
