package com.Alymov.testTaskPlanets.controllers;

import com.Alymov.testTaskPlanets.models.Lord;
import com.Alymov.testTaskPlanets.models.Planet;
import com.Alymov.testTaskPlanets.service.LordService;
import com.Alymov.testTaskPlanets.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lords_of_planets")
public class LordController {

    private final LordService lordService;
    private final PlanetService planetService;

    @Autowired
    public LordController(LordService lordService, PlanetService planetService) {
        this.lordService = lordService;
        this.planetService = planetService;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("lords", lordService.findAll());
        model.addAttribute("planets", planetService.findAllFree());
        return "start_page";
    }
    @GetMapping("/add_lord")
    public String createLord(Model model) {
        model.addAttribute("lord", new Lord());
        return "add_lord";
    }

    @PostMapping("/add_lord")
    public String saveLord(@ModelAttribute(name = "lord") Lord lord) {
        lordService.save(lord);
        return "redirect:/lords_of_planets";
    }

    @DeleteMapping("/delete_lord/{id}")
    public String killLord(@PathVariable(name = "id") Long id) {
        lordService.delete(id);
        return "redirect:/lords_of_planets";
    }

    @GetMapping("/edit_lord/{id}")
    public String editLord(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("lord", lordService.findById(id));
        return "edit_lord";
    }

    @PostMapping("/edit_lord/{id}")
    public String updateLord(@PathVariable(name = "id") Long id, @ModelAttribute(name = "lord") Lord lord, Model model) {
        lord.setId(id);
        lordService.update(lord);
        return "redirect:/lords_of_planets";
    }

    @GetMapping("/info_lord/{id}")
    public String getInfoLord(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("lord", lordService.findById(id));
        return "info_lord";
    }

    @GetMapping("/info_lord/{lord_id}/take_planet")
    public String lordSelectionPlanet(@PathVariable(name = "lord_id") Long lord_id, Model model) {
        model.addAttribute("planets", planetService.findAllFree());
        return "take_planet";
    }
    @DeleteMapping("/info_lord/{lord_id}/lord_destroy_planet/{planet_id}")
    public String lordDestroyPlanet(@PathVariable(name = "lord_id") Long lord_id, @PathVariable(name = "planet_id") Long planet_id) {
        planetService.delete(planet_id);
        return "redirect:/lords_of_planets/info_lord/{lord_id}";
    }
    @GetMapping("/info_lord/{lord_id}/edit_lords_planet/{planet_id}")
    public String editLordsPlanet(@PathVariable(name = "planet_id") Long planet_id, @PathVariable(name = "lord_id") Long lord_id, Model model) {
        model.addAttribute("planet", planetService.findById(planet_id));
        return "edit_planet_lords";
    }

    @PostMapping("/info_lord/{lord_id}/edit_lords_planet/{planet_id}")
    public String saveEditLordsPlanet(@ModelAttribute(name = "planet") Planet planet, @PathVariable(name = "planet_id") Long planet_id, @PathVariable(name = "lord_id") Long lord_id) {
        planet.setId(planet_id);
        planet.setLordId(lordService.findById(lord_id).getId());
        planetService.update(planet);
        return "redirect:/lords_of_planets/info_lord/{lord_id}";
    }

    @PostMapping("/info_lord/{lord_id}/take_planet/{planet_id}")
    public String lordTakePlanet(@PathVariable(name = "lord_id") Long lord_id, @PathVariable(name = "planet_id") Long planet_id) {
        lordService.takePlanet(lord_id, planet_id);
        return "redirect:/lords_of_planets/info_lord/{lord_id}";
    }
    @GetMapping("/add_planet")
    public String createPlanet(Model model) {
        model.addAttribute("planet", new Planet());
        return "add_planet";
    }

    @PostMapping("/add_planet")
    public String saveLord(@ModelAttribute(name = "planet") Planet planet) {
        planetService.save(planet);
        return "redirect:/lords_of_planets";
    }
    @GetMapping("/edit_planet/{id}")
    public String editPlanet(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("planet", planetService.findById(id));
        return "edit_planet";
    }

    @PostMapping("/edit_planet/{id}")
    public String updatePlanet(@PathVariable(name = "id") Long id, @ModelAttribute(name = "planet") Planet planet, Model model) {
        planet.setId(id);
        planetService.update(planet);
        return "redirect:/lords_of_planets";
    }
    @DeleteMapping("/delete_planet/{id}")
    public String destroyPlanet(@PathVariable(name = "id") Long id) {
        planetService.delete(id);
        return "redirect:/lords_of_planets";
    }
    @GetMapping("/top_10_young_lords")
    public String viewTopTenYoungLords(Model model) {
        model.addAttribute("lords", lordService.findTopTenYoungLords());
        return "top_10_young_lords";
    }

    @GetMapping("/list_lazy_lords")
    public String viewLazyLords(Model model) {
        model.addAttribute("lords", lordService.findLazyLords());
        return "list_lazy_lords";
    }

}
