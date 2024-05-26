package top.haidong556.ac.mapper.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import top.haidong556.ac.entity.operationDetail.OperationItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class OperationTypeHandler extends BaseTypeHandler<OperationItem.OperationType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, OperationItem.OperationType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.ordinal());
    }

    @Override
    public OperationItem.OperationType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int type = rs.getInt(columnName);
        return OperationItem.OperationType.values()[type];
    }

    @Override
    public OperationItem.OperationType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int type = rs.getInt(columnIndex);
        return OperationItem.OperationType.values()[type];
    }

    @Override
    public OperationItem.OperationType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int type = cs.getInt(columnIndex);
        return OperationItem.OperationType.values()[type];
    }
}
