package com.uade.tpo.rescate_fauna.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.rescate_fauna.services.AnimalService;
import com.uade.tpo.rescate_fauna.entity.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("animals")
public class AnimalsController {
    private final AnimalService service;

    public AnimalsController(AnimalService service) {
        this.service = service;
    }

    @GetMapping
    public List<Animal> getAnimals() {
        return service.getAllAnimals();
    }

    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable String id) {
        return service.getAnimalById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado con id " + id));
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal){
        return service.createAnimal(animal);
    }

    @PutMapping("/{id}")
    public Animal actualizarAnimal(@PathVariable String id, @RequestBody Animal animal) {
        return service.updateAnimal(id, animal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable String id) {
        service.deleteAnimal(id);
    }
}
