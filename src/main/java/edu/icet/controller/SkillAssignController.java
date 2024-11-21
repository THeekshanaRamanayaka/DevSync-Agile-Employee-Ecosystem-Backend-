package edu.icet.controller;

import edu.icet.dto.SkillAssign;
import edu.icet.service.SkillAssignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/skill-assignment")
@RequiredArgsConstructor
public class SkillAssignController {
    private final SkillAssignService skillAssignService;

    @PostMapping("/add-skill-assign")
    public ResponseEntity<Map<String, Object>> assignSkillToEmployee(@RequestBody SkillAssign skillAssign) {
        Map<String, Object> response = new HashMap<>();
        try {
            skillAssignService.assignSkillToEmployee(skillAssign);
            response.put("status", "SUCCESS");
            response.put("message", "Skill assigned successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, Object>> getAllSkillAssignments() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Skill assignments retrieved successfully");
            response.put("data", skillAssignService.getAllSkillAssignments());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-skill-assign-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getSkillAssignmentById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            SkillAssign skillAssignment = skillAssignService.getSkillAssignmentById(id)
                    .orElseThrow(() -> new RuntimeException("Skill assignment not found"));
            response.put("status", "SUCCESS");
            response.put("message", "Skill assignment retrieved successfully");
            response.put("data", skillAssignment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-skill-assignment-by-id/{employeeId}")
    public ResponseEntity<Map<String, Object>> getSkillAssignmentsByEmployeeId(@PathVariable Long employeeId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Skill assignments retrieved successfully for employee");
            response.put("data", skillAssignService.getSkillAssignmentsByEmployeeId(employeeId));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-skill-assign/{id}")
    public ResponseEntity<Map<String, Object>> updateSkillAssignment(@PathVariable Long id, @RequestBody SkillAssign skillAssign) {
        Map<String, Object> response = new HashMap<>();
        try {
            skillAssignService.updateSkillAssignment(id, skillAssign);
            response.put("status", "SUCCESS");
            response.put("message", "Skill assignment updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete-skill-assign/{id}")
    public ResponseEntity<Map<String, Object>> deleteSkillAssignment(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            skillAssignService.deleteSkillAssignment(id);
            response.put("status", "SUCCESS");
            response.put("message", "Skill assignment deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}