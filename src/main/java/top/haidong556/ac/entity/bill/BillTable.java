package top.haidong556.ac.entity.bill;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillTable {
    private List<BillItem> billItems;
    private LocalDateTime createTime;

    // 私有构造方法，防止外部直接使用 new 来创建实例
    private BillTable(Builder builder) {
        this.billItems = builder.billItems;
        this.createTime = builder.createTime;
    }

    // Getter 方法
    public List<BillItem> getBillItems() {
        return billItems;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    // Builder 静态内部类
    public static class Builder {
        private List<BillItem> billItems = new ArrayList<>();
        private LocalDateTime createTime;

        public Builder() {
        }

        // 设置账单项目的方法，返回 Builder 实例以便链式调用
        public Builder withBillItems(List<BillItem> billItems) {
            this.billItems = billItems;
            return this;
        }

        // 设置创建时间的方法，返回 Builder 实例以便链式调用
        public Builder withCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        // 构建最终的 BillTable 实例的方法
        public BillTable build() {
            return new BillTable(this);
        }
    }
}
