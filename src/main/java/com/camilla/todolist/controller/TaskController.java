package com.camilla.todolist.controller;

import com.camilla.todolist.model.Task;
import com.camilla.todolist.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tasks")
class TaskController {
    private final TaskRepository taskRepository;

    private final String DONE_TASK_TEXT = "done";

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PutMapping("/{id}/{action}")
    public ResponseEntity<Task> markAsDone(@PathVariable Long id, @PathVariable String action) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setDone(Objects.equals(action, DONE_TASK_TEXT));
                    taskRepository.save(task);
                    return ResponseEntity.ok(task);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
