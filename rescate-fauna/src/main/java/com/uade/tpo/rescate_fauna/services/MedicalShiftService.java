package com.uade.tpo.rescate_fauna.services;
import com.uade.tpo.rescate_fauna.repository.MedicalShiftRepository;
import java.util.Optional;
import com.uade.tpo.rescate_fauna.entity.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MedicalShiftService {
    private final MedicalShiftRepository medicalShiftRepository;

    public MedicalShiftService(MedicalShiftRepository medicalShiftRepository) {
        this.medicalShiftRepository = medicalShiftRepository;
    }

    public List<MedicalShift> getAllMedicalShifts(){
        return medicalShiftRepository.findAll();
    }

    public Optional<MedicalShift> getMedicalShiftById(String id) {
        return medicalShiftRepository.findById(id);
    }

    public MedicalShift createMedicalShift(MedicalShift medicalShift) {
        return medicalShiftRepository.save(medicalShift);
    }

    public MedicalShift updateMedicalShift(String id, MedicalShift medicalShiftDetails) {
        return medicalShiftRepository.findById(id)
                .map(medicalShift -> {
                    medicalShift.setIdPatient(medicalShiftDetails.getIdPatient());
                    medicalShift.setIdDoctor(medicalShiftDetails.getIdDoctor());
                    medicalShift.setDate(medicalShiftDetails.getDate());
                    return medicalShiftRepository.save(medicalShift);
                })
                .orElseThrow(() -> new RuntimeException("Turno médico no encontrado con id " + id));
    }
}
