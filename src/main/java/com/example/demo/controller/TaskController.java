package com.example.demo.controller;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task create(@RequestBody CreateTaskRequest request, Authentication auth) {
        return taskService.create(
                auth.getName(),
                request.getTitle(),
                request.getDescription()
        );
    }

    @GetMapping
    public List<Task> myTasks(Authentication auth) {
        return taskService.getUserTasks(auth.getName());
    }

    @GetMapping("/all")
    public List<Task> allTasks() {
        return taskService.getAllTasks();
    }
}