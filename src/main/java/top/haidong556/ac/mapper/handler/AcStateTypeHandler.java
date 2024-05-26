package top.haidong556.ac.mapper.handler;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import top.haidong556.ac.entity.ac.Ac;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcStateTypeHandler extends BaseTypeHandler<Ac.AcState> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Ac.AcState parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.ordinal());
    }

    @Override
    public Ac.AcState getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int ordinal = rs.getInt(columnName);
        return Ac.AcState.values()[ordinal];
    }

    @Override
    public Ac.AcState getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int ordinal = rs.getInt(columnIndex);
        return Ac.AcState.values()[ordinal];
    }

    @Override
    public Ac.AcState getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        int ordinal = cs.getInt(columnIndex);
        return Ac.AcState.values()[ordinal];
    }
}
