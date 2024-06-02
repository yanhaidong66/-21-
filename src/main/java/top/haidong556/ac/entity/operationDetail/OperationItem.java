package top.haidong556.ac.entity.operationDetail;

import lombok.Data;
import top.haidong556.ac.util.GlobalConfig;

import java.time.LocalDateTime;


@Data
public class OperationItem {
    // 枚举类型 OperationType
    public enum OperationType {
        CLOSE_AC, OPEN_AC, CHANGE_AC_TEMP, CHANGE_AC_WIND_SPEED,NO_OPERATION;
    }
    public OperationItem(){}

    // 私有构造函数，使用 Builder 来创建 OperationItem 实例
    private OperationItem(Builder builder) {
        this.operationId = builder.operationId;
        this.userId = builder.userId;
        this.type = builder.type;
        this.createTime = builder.createTime;
        this.acId = builder.acId;
        this.acTemp=builder.acTemp;
        this.acWindSpeed= builder.acWindSpeed;
    }

    // 静态内部类 Builder
    public static class Builder {
        private int operationId;
        private int userId= GlobalConfig.SYSTEM_ID;
        private OperationType type=OperationType.NO_OPERATION;
        private LocalDateTime createTime=LocalDateTime.now();
        private int acId;
        private int acWindSpeed=2;
        private int acTemp=GlobalConfig.AC_DEFAULT_TEMP;

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

        public Builder setAcWindSpeed(int acWindSpeed) {
            if(acWindSpeed>3)
                this.acWindSpeed=3;
            else if(acWindSpeed<1)
                this.acWindSpeed=1;
            else if(acWindSpeed>=1&&acWindSpeed<=3)
                this.acWindSpeed=acWindSpeed;
            return this;
        }

        public Builder setAcTemp(int acTemp) {
            this.acTemp = acTemp;
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
    private int acTemp;
    private int acWindSpeed;
}
