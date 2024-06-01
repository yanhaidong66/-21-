package top.haidong556.ac.entity.bill;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BillItem {
    private int billId;
    private BillState state; // 0是已办理入住，1是已结账
    private LocalDateTime createTime;
    private int userId;
    private int acId;
    private float cost;

    // 枚举类型 BillState
    public enum BillState {
        NOT_PAY, HAVE_PAY;
    }
    public BillItem(){}

    // 私有构造函数，使用 Builder 来创建 BillItem 实例
    private BillItem(Builder builder) {
        this.billId = builder.billId;
        this.state = builder.state;
        this.createTime = builder.createTime;
        this.userId = builder.userId;
        this.acId = builder.acId;
        this.cost=builder.cost;
    }

    // 静态内部类 Builder
    public static class Builder {
        private int billId;
        private BillState state;
        private LocalDateTime createTime;
        private int userId;
        private int acId;
        private float cost;

        // 设置 billId 属性
        public Builder setBillId(int billId) {
            this.billId = billId;
            return this;
        }

        // 设置 state 属性
        public Builder setState(BillState state) {
            this.state = state;
            return this;
        }

        // 设置 createTime 属性
        public Builder setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        // 设置 userId 属性
        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        // 设置 acId 属性
        public Builder setAcId(int acId) {
            this.acId = acId;
            return this;
        }
        public Builder setCost(float cost){
            this.cost=cost;
            return this;
        }

        // 构建 BillItem 实例
        public BillItem build() {
            return new BillItem(this);
        }
    }
}