package com.example.oop12.service;

import com.example.oop12.model.Task;
import com.example.oop12.repository.TaskRepository;
import com.example.oop12.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        System.out.println("Getting all tasks");
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
        System.out.println("Getting task by id: " + id);
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    public Task createTask(Task task) {
        System.out.println("Creating task: " + task.getTitle());
        return repository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        System.out.println("Updating task id: " + id); //таски также выводятся в консоли
        Task existing = getTaskById(id);
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setStatus(updatedTask.getStatus());
        return repository.save(existing);
    }

    public void deleteTask(Long id) {
        System.out.println("Deleting task id: " + id);
        repository.deleteById(id);
    }
}