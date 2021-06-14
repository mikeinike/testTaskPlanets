package com.Alymov.testTaskPlanets.rowmapper;

import com.Alymov.testTaskPlanets.models.Lord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LordRowMapper implements RowMapper<Lord> {
    @Override
    public Lord mapRow(ResultSet resultSet, int i) throws SQLException {
        Lord lord = new Lord();

        lord.setId(resultSet.getLong("id"));
        lord.setName(resultSet.getString("name"));
        lord.setAge(resultSet.getInt("age"));

        return lord;
    }
}
