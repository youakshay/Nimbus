package com.service.Nimbus.Service;

import com.service.Nimbus.DTO.UpdatePassword;
import com.service.Nimbus.Model.User;
import com.service.Nimbus.Respository.CrudRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginAndRegister {
    private final CrudRepo repository;
    private PasswordEncoder passwordEncoder;


    public LoginAndRegister(CrudRepo repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User userDetails) {
        if(repository.existsByEmail(userDetails.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }
        if(repository.existsByUsername(userDetails.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }
        String password = userDetails.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        User updatedUserDetails = new User(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFullName(),
                userDetails.getEmail(),
                hashedPassword,
                userDetails.getUserRole(),
                userDetails.getCreatedAt()
        );
        repository.save(updatedUserDetails);
        System.out.println("User registered successfully.");
    }
}
