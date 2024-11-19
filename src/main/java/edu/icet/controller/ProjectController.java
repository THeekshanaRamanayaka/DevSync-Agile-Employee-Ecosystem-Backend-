package edu.icet.controller;

import edu.icet.dto.Project;
import edu.icet.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/add-project")
    public ResponseEntity<Map<String, Object>> createProject(@RequestBody Project project) {
        Map<String, Object> response = new HashMap<>();
        try {
            projectService.saveProject(project);
            response.put("status", "SUCCESS");
            response.put("message", "Project created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, Object>> getAllProjects() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Projects retrieved successfully");
            response.put("data", projectService.getAllProjects());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-project-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getProjectById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Project retrieved successfully");
            response.put("data", projectService.getProjectById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        Map<String, Object> response = new HashMap<>();
        try {
            projectService.updateProject(id, updatedProject);
            response.put("status", "SUCCESS");
            response.put("message", "Project updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<Map<String, Object>> completeProject(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            projectService.completeProject(id);
            response.put("status", "SUCCESS");
            response.put("message", "Project marked as completed successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
