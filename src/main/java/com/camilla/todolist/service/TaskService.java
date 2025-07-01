/* package src.main.java.com.camilla.todolist.service;

public class TaskService {
    
}
 */
// TaskService.java
package com.camilla.todolist.service;

import com.camilla.todolist.model.Task;
import com.camilla.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository repository;

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
