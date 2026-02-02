package com.xiang.zerotrade.infrastructure.persistence.typeHandler;

import com.xiang.zerotrade.domain.market.pair.ContractRule;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import tools.jackson.databind.ObjectMapper;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author linshunxiang
 */

@MappedTypes(ContractRule.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ContractRuleTypeHandler extends BaseTypeHandler<ContractRule> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public ContractRule getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        if (json == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, ContractRule.class);
        } catch (Exception e) {
            throw new SQLException("Failed to parse ContractRule json", e);
        }
    }

    @Override
    public ContractRule getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public ContractRule getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public void setNonNullParameter(
            PreparedStatement ps, int i, ContractRule parameter, JdbcType jdbcType)
            throws SQLException {
        try {
            ps.setString(i, MAPPER.writeValueAsString(parameter));
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
