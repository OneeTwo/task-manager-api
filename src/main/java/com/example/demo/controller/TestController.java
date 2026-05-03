package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/user/profile")
    public String userProfile() {
        return "user profile";
    }

    @GetMapping("/admin/panel")
    public String adminPanel() {
        return "admin panel";
    }
}