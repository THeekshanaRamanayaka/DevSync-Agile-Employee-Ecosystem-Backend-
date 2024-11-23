package edu.icet.controller;

import edu.icet.dto.Employee;
import edu.icet.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/add-employee")
    public ResponseEntity<Map<String, Object>> createEmployee(@RequestBody Employee employee) {
        Map<String, Object> response = new HashMap<>();
        try {
            employeeService.saveEmployee(employee);
            response.put("status", "SUCCESS");
            response.put("message", "Employee added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, Object>> getAllEmployee() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            response.put("status", "SUCCESS");
            response.put("message", "Employees retrieved successfully");
            response.put("data", employees);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-employee-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Employee employee = employeeService.getEmployeeById(id);response.put("status", "SUCCESS");
            response.put("message", "Employee retrieved successfully");
            response.put("data", employee);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-employee/{id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Map<String, Object> response = new HashMap<>();
        try {
            employeeService.updateEmployee(id, employee);
            response.put("status", "SUCCESS");
            response.put("message", "Employee updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            employeeService.deleteEmployee(id);
            response.put("status", "SUCCESS");
            response.put("message", "Employee deactivated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
