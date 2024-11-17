package edu.icet.controller;

import edu.icet.dto.Department;
import edu.icet.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("add-department")
    public ResponseEntity<Map<String, Object>> createDepartment(@RequestBody Department department) {
        Map<String, Object> response = new HashMap<>();
        try {
            departmentService.saveDepartment(department);
            response.put("status", "SUCCESS");
            response.put("message", "Department created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, Object>> getAllDepartments() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Departments retrieved successfully");
            response.put("data", departmentService.getAllDepartments());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-department/{id}")
    public ResponseEntity<Map<String, Object>> getDepartmentById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Department retrieved successfully");
            response.put("data", departmentService.getDepartmentById(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-department/{id}")
    public ResponseEntity<Map<String, Object>> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Map<String, Object> response = new HashMap<>();
        try {
            departmentService.updateDepartment(id, department);
            response.put("status", "SUCCESS");
            response.put("message", "Department updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<Map<String, Object>> deactivateDepartment(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            departmentService.deactivateDepartment(id);
            response.put("status", "SUCCESS");
            response.put("message", "Department deactivated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
