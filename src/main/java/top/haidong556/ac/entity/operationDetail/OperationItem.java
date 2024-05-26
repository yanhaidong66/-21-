package top.haidong556.ac.entity.operationDetail;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationItem {
    public enum OperationType {
        OPEN_AC, CLOSE_AC, CHANGE_AC_TEMP, CHANGE_AC_WIND_SPEED;
    }

    int operationId;
    int userId;
    OperationType type;
    LocalDateTime createTime;
    int acId;


}
