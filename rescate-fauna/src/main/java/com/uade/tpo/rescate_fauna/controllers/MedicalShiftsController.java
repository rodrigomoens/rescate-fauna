package com.uade.tpo.rescate_fauna.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.rescate_fauna.services.MedicalShiftService;
import com.uade.tpo.rescate_fauna.entity.*;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/medical-shifts")
public class MedicalShiftsController {
    private final MedicalShiftService service;

    public MedicalShiftsController(MedicalShiftService service){
        this.service = service;
    }

    @GetMapping
    public List<MedicalShift> getMedicalShifts(){
        return service.getAllMedicalShifts();
    }

    @GetMapping("/{id}")
    public MedicalShift getMedicalShift(@PathVariable String id) {
        return service.getMedicalShiftById(id).orElseThrow(() -> new RuntimeException("Turno médico no encontrado con id " + id));
    }

    @PostMapping
    public MedicalShift createMedicalShift(@RequestBody MedicalShift medicalShift) {
        return service.createMedicalShift(medicalShift);
    }

    @PutMapping("/{id}")
    public MedicalShift updateMedicalShift(@PathVariable String id, @RequestBody MedicalShift medicalShift) {
        return service.updateMedicalShift(id, medicalShift);
    }
    
    
}
