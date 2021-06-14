package com.Alymov.testTaskPlanets.repository;

import com.Alymov.testTaskPlanets.models.Lord;
import com.Alymov.testTaskPlanets.rowmapper.LordRowMapper;
import com.Alymov.testTaskPlanets.rowmapper.PlanetRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LordRepository {

    private final JdbcTemplate jdbcTemplate;

    public LordRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Lord> findAll(){
        return jdbcTemplate.query("SELECT * FROM lords",new LordRowMapper());
    }

    public void save(Lord lord){
        jdbcTemplate.update("INSERT INTO lords(name , age ) VALUES (?,?)", lord.getName(),lord.getAge());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM lords WHERE id=?" , id );
    }

    public void update(Lord lord){
        jdbcTemplate.update("UPDATE lords SET name = ?, age = ? WHERE id = ?",
                lord.getName(), lord.getAge(), lord.getId());
    }

    public Lord findById(Long id){
        Lord lord = jdbcTemplate.query("SELECT * FROM lords WHERE id = ? ",
                new LordRowMapper(),
                id)
                .stream().findAny().orElse(null);
        lord.setPlanets(jdbcTemplate.query("SELECT * FROM planets WHERE lord_id = ?",new PlanetRowMapper(),lord.getId()));
        return lord;
    }

    public void takePlanet(Long lordId, Long planetId){
        jdbcTemplate.update("UPDATE planets SET lord_id = ? WHERE id = ?", lordId, planetId);
    }

    public List<Lord> findTopTenYoungLords(){
        return jdbcTemplate.query("SELECT * FROM lords ORDER BY age ASC limit 10", new LordRowMapper());
    }

    public List<Lord> findLazyLords(){
        return jdbcTemplate.query("SELECT * FROM lords l LEFT JOIN planets p ON l.id = p.lord_id WHERE p.lord_id IS NULL",new LordRowMapper());
    }


}
