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
import com.uade.tpo.rescate_fauna.services.MedicalHistoryService;
import com.uade.tpo.rescate_fauna.entity.*;
import org.springframework.web.bind.annotation.PutMapping;
/**
 * Stub HabitController removed (endpoints moved to PatientsController).
 * This minimal class exists to avoid compilation errors from the old file.
 */
@RestController
@RequestMapping("/api/historiales-medicos")
public class MedicalHistoryController {
    private final MedicalHistoryService service;

    public MedicalHistoryController(MedicalHistoryService service){
        this.service = service;
    }

    @GetMapping("/{pacienteId}")
    public List<MedicalHistory> getMedicalHistories(@PathVariable String pacienteId){
        return service.getMedicalHistories(pacienteId);
    }

    @PostMapping("/{pacienteId}")
    public MedicalHistory addMedicalHistory(@PathVariable String pacienteId, @RequestBody MedicalHistory medicalHistory) {
        return service.addMedicalHistory(pacienteId, medicalHistory);
    }

    @PutMapping("/{pacienteId}/{historiaId}")
    public MedicalHistory updateMedicalHistory(@PathVariable String pacienteId, @PathVariable String historiaId, @RequestBody MedicalHistory nuevaHistoria) {
        return service.updateMedicalHistory(pacienteId, historiaId, nuevaHistoria);
    }

    @DeleteMapping("/{pacienteId}/{historiaId}")
    public void deleteMedicalHistory(@PathVariable String pacienteId, @PathVariable String historiaId) {
        service.deleteMedicalHistory(pacienteId, historiaId);
    }    
}
