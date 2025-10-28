package com.uade.tpo.rescate_fauna.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.uade.tpo.rescate_fauna.entity.User;

public interface UserRepository extends MongoRepository<User, String>{ 
}
