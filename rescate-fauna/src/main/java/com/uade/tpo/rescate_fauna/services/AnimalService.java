package com.uade.tpo.rescate_fauna.services;
import com.uade.tpo.rescate_fauna.repository.AnimalRepository;
import java.util.Optional;
import com.uade.tpo.rescate_fauna.entity.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAllAnimals(){
        return animalRepository.findAll();
    }

    public Optional<Animal> getAnimalById(String id) {
        return animalRepository.findById(id);
    }

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

     public Animal updateAnimal(String id, Animal animalDetails) {
        return animalRepository.findById(id)
                .map(animal -> {
                    animal.setNombreComun(animalDetails.getNombreComun());
                    animal.setNombreCientifico(animalDetails.getNombreCientifico());
                    animal.setCategoriaConservacion(animalDetails.getCategoriaConservacion());
                    animal.setEdadAproximada(animalDetails.getEdadAproximada());
                    animal.setGenero(animalDetails.getGenero());
                    animal.setIdentificadorChip(animalDetails.getIdentificadorChip());
                    animal.setHistorialMedico(animalDetails.getHistorialMedico());
                    return animalRepository.save(animal);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }

    public void deleteAnimal(String id) {
        if (!animalRepository.existsById(id)) {
            throw new RuntimeException("Animal no encontrado con id " + id);
        }
        animalRepository.deleteById(id);
    }
}
