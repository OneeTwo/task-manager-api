package com.example.demo.service;

import com.example.demo.dto.TaskResponse;
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

    public TaskResponse create(String email,
                               String title,
                               String description) {

        User newUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = Task.builder()
                .title(title)
                .description(description)
                .user(newUser)
                .build();

        Task saved = taskRepository.save(task);

        return map(saved);
    }

    public List<TaskResponse> getUserTasks(String email) {

        User newUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByUser(newUser)
                .stream()
                .map(this::map)
                .toList();
    }

    public List<TaskResponse> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public TaskResponse update(Long id,
                               String email,
                               String title,
                               String description) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Access denied");
        }

        task.setTitle(title);
        task.setDescription(description);

        Task updated = taskRepository.save(task);

        return map(updated);
    }

    public void delete(Long id, String email) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Access denied");
        }

        taskRepository.delete(task);
    }

    private TaskResponse map(Task task) {

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .userEmail(task.getUser().getEmail())
                .build();
    }
}