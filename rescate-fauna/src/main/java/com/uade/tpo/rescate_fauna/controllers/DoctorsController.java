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

import com.uade.tpo.rescate_fauna.services.DoctorService;
import com.uade.tpo.rescate_fauna.entity.*;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {
    private final DoctorService service;

    public DoctorsController(DoctorService service){
        this.service = service;
    }

    @GetMapping
    public List<Doctor> getDoctors(){
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctor(@PathVariable String id) {
        return service.getDoctorById(id).orElseThrow(() -> new RuntimeException("Doctor no encontrado con id " + id));
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return service.createDoctor(doctor);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable String id, @RequestBody Doctor doctor) {
        return service.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable String id) {
        service.deleteDoctor(id);
    }
    
    
}

