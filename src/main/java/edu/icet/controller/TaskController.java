package edu.icet.controller;

import edu.icet.dto.Task;
import edu.icet.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add-task")
    public ResponseEntity<Map<String, Object>> createTask(@RequestBody Task task) {
        Map<String, Object> response = new HashMap<>();
        try {
            taskService.createTask(task);
            response.put("status", "SUCCESS");
            response.put("message", "Task created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, Object>> getAllTasks() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Tasks retrieved successfully");
            response.put("data", taskService.getAllTasks());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-task-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getTaskById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Task task = taskService.getTaskById(id)
                    .orElseThrow(() -> new RuntimeException("Task not found"));
            response.put("status", "SUCCESS");
            response.put("message", "Task retrieved successfully");
            response.put("data", task);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Map<String, Object> response = new HashMap<>();
        try {
            taskService.updateTask(id, task);
            response.put("status", "SUCCESS");
            response.put("message", "Task updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            taskService.deleteTask(id);
            response.put("status", "SUCCESS");
            response.put("message", "Task deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-tasks-by-project/{projectId}")
    public ResponseEntity<Map<String, Object>> getTasksByProject(@PathVariable Long projectId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Tasks retrieved successfully");
            response.put("data", taskService.getTasksByProjectId(projectId));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-tasks-by-employee/{employeeId}")
    public ResponseEntity<Map<String, Object>> getTasksByEmployee(@PathVariable Long employeeId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Tasks retrieved successfully");
            response.put("data", taskService.getTasksByEmployeeId(employeeId));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
