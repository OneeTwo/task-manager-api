package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User newUser = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();

        return userRepository.save(newUser);
    }

    public User login(String email, String password) {
        User newUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, newUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return newUser;
    }
}