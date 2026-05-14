package com.example.demo.controller;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UpdateTaskRequest;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
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
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest request, Authentication auth) {
        return taskService.create(
                auth.getName(),
                request.getTitle(),
                request.getDescription()
        );
    }

    @GetMapping
    public List<TaskResponse> myTasks(Authentication auth) {
        return taskService.getUserTasks(auth.getName());
    }

    @GetMapping("/all")
    public List<TaskResponse> allTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id,
                       @RequestBody UpdateTaskRequest request,
                       Authentication auth) {

        return taskService.update(
                id,
                auth.getName(),
                request.getTitle(),
                request.getDescription()
        );
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id,
                         Authentication auth) {

        taskService.delete(id, auth.getName());

        return "Task deleted";
    }
}