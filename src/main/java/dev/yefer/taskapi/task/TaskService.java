package dev.yefer.taskapi.task;

import dev.yefer.taskapi.task.Task;
import dev.yefer.taskapi.task.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(Task task) {
        task.setId(UUID.randomUUID().toString());
        repository.save(task);
        return task;
    }

    public Optional<Task> getTask(String id) {
        return repository.findById(id);
    }

    public void deleteTask(String id) {
        repository.delete(id);
    }
}

