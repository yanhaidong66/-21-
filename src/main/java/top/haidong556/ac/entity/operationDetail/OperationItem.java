package top.haidong556.ac.entity.operationDetail;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class OperationItem {
    // 枚举类型 OperationType
    public enum OperationType {
        CLOSE_AC, OPEN_AC, CHANGE_AC_TEMP, CHANGE_AC_WIND_SPEED;
    }
    public OperationItem(){}

    // 私有构造函数，使用 Builder 来创建 OperationItem 实例
    private OperationItem(Builder builder) {
        this.operationId = builder.operationId;
        this.userId = builder.userId;
        this.type = builder.type;
        this.createTime = builder.createTime;
        this.acId = builder.acId;
    }

    // 静态内部类 Builder
    public static class Builder {
        private int operationId;
        private int userId;
        private OperationType type;
        private LocalDateTime createTime;
        private int acId;

        // 设置 operationId 属性
        public Builder setOperationId(int operationId) {
            this.operationId = operationId;
            return this;
        }

        // 设置 userId 属性
        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        // 设置 type 属性
        public Builder setType(OperationType type) {
            this.type = type;
            return this;
        }

        // 设置 createTime 属性
        public Builder setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        // 设置 acId 属性
        public Builder setAcId(int acId) {
            this.acId = acId;
            return this;
        }

        // 构建 OperationItem 实例
        public OperationItem build() {
            return new OperationItem(this);
        }
    }

    // OperationItem 类的属性
    private int operationId;
    private int userId;
    private OperationType type;
    private LocalDateTime createTime;
    private int acId;
}
