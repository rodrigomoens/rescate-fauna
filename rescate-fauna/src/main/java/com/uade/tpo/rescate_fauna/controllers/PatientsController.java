package com.uade.tpo.rescate_fauna.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.rescate_fauna.services.PatientService;
import com.uade.tpo.rescate_fauna.entity.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {
    private final PatientService service;

    public PatientsController(PatientService service){
        this.service = service;
    }

    @GetMapping
    public List<Patient> getPatients(){
        return service.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable String id) {
        return service.getPatientById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado con id " + id));
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return service.createPatient(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable String id, @RequestBody Patient patient) {
        return service.updatePatient(id, patient);
    }
    
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        service.deletePatient(id);
    }

    
    
    
}
