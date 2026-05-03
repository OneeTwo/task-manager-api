package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        User newUser = userService.register(
                request.getEmail(),
                request.getPassword()
        );

        return UserResponse.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .role(newUser.getRole().name())
                .build();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        User newUser = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        return jwtService.generateToken(
                newUser.getEmail(),
                newUser.getRole().name()
        );
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}