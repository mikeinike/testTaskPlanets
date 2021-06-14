package com.Alymov.testTaskPlanets.service;

import com.Alymov.testTaskPlanets.models.Planet;
import com.Alymov.testTaskPlanets.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService {
    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public void save(Planet planet) {
        planetRepository.save(planet);
    }

    public List<Planet> findAllFree() {

        return planetRepository.findAllFree();
    }

    public void delete(Long id) {
        planetRepository.delete(id);
    }

    public Planet findById(Long id) {
        return planetRepository.findById(id);
    }

    public void update(Planet planet) {
        planetRepository.update(planet);
    }
}
