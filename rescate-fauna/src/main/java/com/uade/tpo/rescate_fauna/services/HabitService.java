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
public class HabitService {

    private final PatientRepository patientRepository;

    public HabitService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Habit> getHabits(String pacienteId) {
        Patient paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return paciente.getHabitos();
    }

    public Habit addHabit(String pacienteId, Habit habito) {
        Patient paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.getHabitos().add(habito);
        patientRepository.save(paciente);
        return habito;
    }

    public Habit updateHabit(String pacienteId, String habitoId, Habit nuevoHabito) {
        Patient paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.getHabitos().replaceAll(h -> h.getId().equals(habitoId) ? nuevoHabito : h);
        patientRepository.save(paciente);
        return nuevoHabito;
    }

    public void deleteHabit(String pacienteId, String habitoId) {
        Patient paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.getHabitos().removeIf(h -> h.getId().equals(habitoId));
        patientRepository.save(paciente);
    }

    public Optional<Habit> getHabitById(String pacienteId, String habitoId) {
        Patient paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return paciente.getHabitos().stream()
                .filter(h -> h.getId().equals(habitoId))
                .findFirst();
    }
}
