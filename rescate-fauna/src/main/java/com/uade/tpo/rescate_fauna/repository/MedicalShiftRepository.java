package com.uade.tpo.rescate_fauna.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.uade.tpo.rescate_fauna.entity.MedicalShift;

public interface MedicalShiftRepository extends MongoRepository<MedicalShift, String>{ 
}
