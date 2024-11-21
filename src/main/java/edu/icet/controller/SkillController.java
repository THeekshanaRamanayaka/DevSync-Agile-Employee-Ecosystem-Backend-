package edu.icet.controller;

import edu.icet.dto.Skill;
import edu.icet.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @PostMapping("/add-skill")
    public ResponseEntity<Map<String, Object>> createSkill(@RequestBody Skill skill) {
        Map<String, Object> response = new HashMap<>();
        try {
            skillService.createSkill(skill);
            response.put("status", "SUCCESS");
            response.put("message", "Skill created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, Object>> getAllSkills() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Skills retrieved successfully");
            response.put("data", skillService.getAllSkills());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("get-skill-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getSkillById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Skill skill = skillService.getSkillById(id)
                    .orElseThrow(() -> new RuntimeException("Skill not found"));
            response.put("status", "SUCCESS");
            response.put("message", "Skill retrieved successfully");
            response.put("data", skill);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("update-skill/{id}")
    public ResponseEntity<Map<String, Object>> updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        Map<String, Object> response = new HashMap<>();
        try {
            skillService.updateSkill(id, skill);
            response.put("status", "SUCCESS");
            response.put("message", "Skill updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("delete-skill/{id}")
    public ResponseEntity<Map<String, Object>> deleteSkill(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            skillService.deleteSkill(id);
            response.put("status", "SUCCESS");
            response.put("message", "Skill deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
