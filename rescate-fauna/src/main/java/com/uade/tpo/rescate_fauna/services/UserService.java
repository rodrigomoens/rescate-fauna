package com.uade.tpo.rescate_fauna.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.rescate_fauna.entity.User;
import com.uade.tpo.rescate_fauna.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        if (user.getNombre() == null || user.getApellido() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Faltan datos obligatorios para el registro del usuario.");
        }

        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        // Hash password
        String hashed = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashed);

        User saved = userRepository.save(user);
        // Do not return the password
        //saved.setPassword(null);
        return saved;
    }

        public User authenticate(String email, String rawPassword) {
            Optional<User> existing = userRepository.findByEmail(email);
            if (existing.isEmpty()) {
                throw new IllegalArgumentException("Credenciales inválidas.");
            }
            User found = existing.get();
            if (!passwordEncoder.matches(rawPassword, found.getPassword())) {
                throw new IllegalArgumentException("Credenciales inválidas.");
            }
            // Do not expose password
            found.setPassword(null);
            return found;
        }

}