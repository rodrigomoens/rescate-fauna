package com.uade.tpo.rescate_fauna.services;
import com.uade.tpo.rescate_fauna.repository.UserRepository;
import java.util.Optional;
import com.uade.tpo.rescate_fauna.entity.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(userDetails.getEmail());
                    user.setPassword(userDetails.getPassword());
                    user.setNombre(userDetails.getNombre());
                    user.setApellido(userDetails.getApellido());
                    user.setRol(userDetails.getRol());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User no encontrado con id " + id));
    }
}