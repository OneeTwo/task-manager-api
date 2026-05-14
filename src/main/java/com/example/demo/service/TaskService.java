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

    public Task update(Long id,
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

        return taskRepository.save(task);
    }

    public void delete(Long id, String email) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Access denied");
        }

        taskRepository.delete(task);
    }
}