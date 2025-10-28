package com.uade.tpo.rescate_fauna.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.uade.tpo.rescate_fauna.entity.Animal;

public interface AnimalRepository extends MongoRepository<Animal, String>{ 
}
