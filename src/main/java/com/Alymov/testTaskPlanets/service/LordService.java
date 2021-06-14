package com.Alymov.testTaskPlanets.service;

import com.Alymov.testTaskPlanets.models.Lord;
import com.Alymov.testTaskPlanets.repository.LordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LordService {

    private final LordRepository lordRepository;

    public LordService(LordRepository lordRepository) {
        this.lordRepository = lordRepository;
    }

    public List<Lord> findAll() {
        return lordRepository.findAll();
    }

    public void save(Lord lord) {
        lordRepository.save(lord);
    }
    public void delete(Long id) {
        lordRepository.delete(id);
    }
    public void update(Lord lord) {
        lordRepository.update(lord);
    }

    public Lord findById(Long id) {
        return lordRepository.findById(id);
    }

    public void takePlanet(Long lordId, Long planetId) {
        lordRepository.takePlanet(lordId,planetId);
    }

    public List<Lord> findTopTenYoungLords() {
        return lordRepository.findTopTenYoungLords();
    }

    public List<Lord> findLazyLords() {
        return lordRepository.findLazyLords();
    }


}
