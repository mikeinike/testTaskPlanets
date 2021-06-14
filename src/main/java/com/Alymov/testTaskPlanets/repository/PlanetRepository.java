package com.Alymov.testTaskPlanets.repository;

import com.Alymov.testTaskPlanets.models.Planet;
import com.Alymov.testTaskPlanets.rowmapper.PlanetRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanetRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlanetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Planet> findAllFree(){
        return jdbcTemplate.query("SELECT * FROM planets WHERE lord_id IS NULL ", new PlanetRowMapper());
    }

    public void save(Planet planet){
        jdbcTemplate.update("INSERT INTO planets (name) VALUES (?)", planet.getName());

    }

    public void update(Planet planet){
        jdbcTemplate.update("UPDATE planets SET name = ?, lord_id = ? WHERE id = ?",
                planet.getName(),planet.getLordId(),planet.getId());
    }

    public void delete(Long id){
        jdbcTemplate.update("DELETE FROM planets WHERE id = ?", id);
    }

    public Planet findById(Long id){
        return jdbcTemplate.query("SELECT * FROM planets WHERE id = ?",
                new PlanetRowMapper(),
                id)
                .stream().findAny().orElse(null);
    }

}
