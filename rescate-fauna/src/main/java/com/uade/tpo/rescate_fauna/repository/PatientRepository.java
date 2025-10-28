package com.uade.tpo.rescate_fauna.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.uade.tpo.rescate_fauna.entity.Patient;

public interface PatientRepository extends MongoRepository<Patient, String>{ 
}
