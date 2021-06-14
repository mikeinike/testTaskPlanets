package com.Alymov.testTaskPlanets.rowmapper;

import com.Alymov.testTaskPlanets.models.Planet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanetRowMapper implements RowMapper<Planet> {
    @Override
    public Planet mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Planet planet = new Planet();

        planet.setId(resultSet.getLong("id"));
        planet.setName(resultSet.getString("name"));
        planet.setLordId(resultSet.getLong("lord_id"));

        return planet;
    }
}
