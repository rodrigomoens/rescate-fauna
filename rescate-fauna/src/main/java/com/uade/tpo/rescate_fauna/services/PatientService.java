package com.uade.tpo.rescate_fauna.services;
import com.uade.tpo.rescate_fauna.repository.PatientRepository;
import java.util.Optional;
import com.uade.tpo.rescate_fauna.entity.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(String id, Patient patientDetails) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setNombre(patientDetails.getNombre());
                    patient.setApellido(patientDetails.getApellido());
                    patient.setDNI(patientDetails.getDNI());
                    patient.setObraSocial(patientDetails.getObraSocial());
                    patient.setHabitos(patientDetails.getHabitos());
                    patient.setHistorialClinico(patientDetails.getHistorialClinico());
                    return patientRepository.save(patient);
                })
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id " + id));
    }

    public void deletePatient(String id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Paciente no encontrado con id " + id);
        }
        patientRepository.deleteById(id);
    }
}

