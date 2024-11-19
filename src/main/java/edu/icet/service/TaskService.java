package edu.icet.service;

import edu.icet.dto.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Long taskId, Task task);
    Optional<Task> getTaskById(Long taskId);
    List<Task> getAllTasks();
    void deleteTask(Long taskId);
    List<Task> getTasksByProjectId(Long projectId);
    List<Task> getTasksByEmployeeId(Long employeeId);
}
