package com.uade.tpo.rescate_fauna.services;

import com.uade.tpo.rescate_fauna.entity.Habit;
import com.uade.tpo.rescate_fauna.entity.Patient;
import com.uade.tpo.rescate_fauna.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.uade.tpo.rescate_fauna.repository.DoctorRepository;
import com.uade.tpo.rescate_fauna.entity.*;

@Service
public class MedicalHistoryService {

    private final PatientRepository patientRepository;

    public MedicalHistoryService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<MedicalHistory> getMedicalHistories(String pacienteId) {
        Patient paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return paciente.getHistorialClinico();
    }

    public MedicalHistory addMedicalHistory(String pacienteId, MedicalHistory historia) {
        Patient paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.getHistorialClinico().add(historia);
        patientRepository.save(paciente);
        return historia;
    }

    public MedicalHistory updateMedicalHistory(String pacienteId, String historiaId, MedicalHistory nuevaHistoria) {
        Patient paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.getHistorialClinico().replaceAll(h -> h.getId().equals(historiaId) ? nuevaHistoria : h);
        patientRepository.save(paciente);
        return nuevaHistoria;
    }

    public void deleteMedicalHistory(String pacienteId, String historiaId) {
        Patient paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.getHistorialClinico().removeIf(h -> h.getId().equals(historiaId));
        patientRepository.save(paciente);
    }

    public Optional<MedicalHistory> getMedicalHistoryById(String pacienteId, String historiaId) {
        Patient paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return paciente.getHistorialClinico().stream()
                .filter(h -> h.getId().equals(historiaId))
                .findFirst();
    }
}
