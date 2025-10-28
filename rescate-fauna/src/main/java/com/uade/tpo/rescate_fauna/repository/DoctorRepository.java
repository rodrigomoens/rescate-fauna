package com.uade.tpo.rescate_fauna.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.uade.tpo.rescate_fauna.entity.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor, String>{ 
}
