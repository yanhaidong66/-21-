package top.haidong556.ac.mapper.handler;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import top.haidong556.ac.entity.role.People.RoleType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleTypeHandler extends BaseTypeHandler<RoleType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, RoleType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public RoleType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String role = rs.getString(columnName);
        return RoleType.valueOf(role);
    }

    @Override
    public RoleType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String role = rs.getString(columnIndex);
        return RoleType.valueOf(role);
    }

    @Override
    public RoleType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String role = cs.getString(columnIndex);
        return RoleType.valueOf(role);
    }
}
