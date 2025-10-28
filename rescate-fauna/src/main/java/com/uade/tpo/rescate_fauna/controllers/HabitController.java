package com.uade.tpo.rescate_fauna.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.rescate_fauna.services.HabitService;
import com.uade.tpo.rescate_fauna.entity.*;
import org.springframework.web.bind.annotation.PutMapping;
/**
 * Stub HabitController removed (endpoints moved to PatientsController).
 * This minimal class exists to avoid compilation errors from the old file.
 */
@RestController
@RequestMapping("/api/habits")
public class HabitController {
    private final HabitService service;

    public HabitController(HabitService service){
        this.service = service;
    }

    @GetMapping("/{pacienteId}")
    public List<Habit> getHabits(@PathVariable String pacienteId){
        return service.getHabits(pacienteId);
    }

    @PostMapping("/{pacienteId}")
    public Habit addHabit(@PathVariable String pacienteId, @RequestBody Habit habit) {
        return service.addHabit(pacienteId, habit);
    }

    @PutMapping("/{pacienteId}/{habitId}")
    public Habit updateHabit(@PathVariable String pacienteId, @PathVariable String habitId, @RequestBody Habit nuevoHabito) {
        return service.updateHabit(pacienteId, habitId, nuevoHabito);
    }

    @DeleteMapping("/{pacienteId}/{habitId}")
    public void deleteHabit(@PathVariable String pacienteId, @PathVariable String habitId) {
        service.deleteHabit(pacienteId, habitId);
    }    
}