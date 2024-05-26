package top.haidong556.ac.mapper.handler;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import top.haidong556.ac.entity.bill.BillItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class BillStateHandler extends BaseTypeHandler<BillItem.BillState> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BillItem.BillState parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.ordinal());
    }

    @Override
    public BillItem.BillState getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int state = rs.getInt(columnName);
        return BillItem.BillState.values()[state];
    }

    @Override
    public BillItem.BillState getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int state = rs.getInt(columnIndex);
        return BillItem.BillState.values()[state];
    }

    @Override
    public BillItem.BillState getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int state = cs.getInt(columnIndex);
        return BillItem.BillState.values()[state];
    }
}
