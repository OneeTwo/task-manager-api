package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task create(String email, String title, String description) {

        User newUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = Task.builder()
                .title(title)
                .description(description)
                .user(newUser)
                .build();

        return taskRepository.save(task);
    }

    public List<Task> getUserTasks(String email) {
        User newUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByUser(newUser);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}