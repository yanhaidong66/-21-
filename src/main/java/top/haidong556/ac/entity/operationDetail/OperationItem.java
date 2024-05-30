package top.haidong556.ac.entity.operationDetail;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationItem {
    public enum OperationType {
         CLOSE_AC,OPEN_AC, CHANGE_AC_TEMP, CHANGE_AC_WIND_SPEED;
    }

    int operationId;
    int userId;
    OperationType type;
    LocalDateTime createTime;
    int acId;
}
