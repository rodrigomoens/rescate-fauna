package com.uade.tpo.rescate_fauna.services;
import com.uade.tpo.rescate_fauna.repository.DoctorRepository;
import java.util.Optional;
import com.uade.tpo.rescate_fauna.entity.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(String id, Doctor doctorDetails) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    doctor.setNombre(doctorDetails.getNombre());
                    doctor.setApellido(doctorDetails.getApellido());
                    doctor.setMatricula(doctorDetails.getMatricula());
                    doctor.setEspecialidad(doctorDetails.getEspecialidad());
                    return doctorRepository.save(doctor);
                })
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con id " + id));
    }

    public void deleteDoctor(String id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor no encontrado con id " + id);
        }
        doctorRepository.deleteById(id);
    }
}