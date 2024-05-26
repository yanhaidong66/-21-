package top.haidong556.ac.entity.bill;

import lombok.Data;

import java.time.LocalDateTime;

@Data
/*在每次入住创建用户后会生成一个账单条目*/
public class BillItem {
    private int bill_id;
    private BillState state;/* 0是已办理入住，1是已结账 */
    LocalDateTime datetime;
    int userId;
    int acId;

    public enum BillState {
        NOT_PAY, HAVE_PAY;
    }
}
