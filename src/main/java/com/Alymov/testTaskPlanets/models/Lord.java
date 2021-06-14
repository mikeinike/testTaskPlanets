package com.Alymov.testTaskPlanets.models;

import lombok.Data;

import java.util.List;

@Data
public class Lord {

    private Long id;

    private String name;
    private int age;
    private List<Planet> planets;
}
