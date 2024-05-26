package top.haidong556.ac.entity.operationDetail;

import java.time.LocalDateTime;
import java.util.List;

/*用户的操作表*/
public class OperationTable {
    private List<OperationItem> operationItems;
    private LocalDateTime createTime;

    private OperationTable(Builder builder) {
        this.operationItems = builder.operationItems;
        this.createTime = builder.createTime;
    }

    public static class Builder {
        private List<OperationItem> operationItems;
        private LocalDateTime createTime;

        public Builder setOperationItems(List<OperationItem> items) {
            this.operationItems = items;
            return this;
        }

        public Builder() {
            createTime = LocalDateTime.now();
        }

        public OperationTable build() {
            return new OperationTable(this);
        }

    }
}
