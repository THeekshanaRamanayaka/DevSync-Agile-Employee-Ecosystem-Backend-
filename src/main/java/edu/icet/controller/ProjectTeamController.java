package edu.icet.controller;

import edu.icet.dto.ProjectTeam;
import edu.icet.service.ProjectTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/project-team")
@RequiredArgsConstructor
public class ProjectTeamController {

    private final ProjectTeamService projectTeamService;

    @PostMapping("/add-team-member")
    public ResponseEntity<Map<String, Object>> addTeamMember(@RequestBody ProjectTeam projectTeam) {
        Map<String, Object> response = new HashMap<>();
        try {
            projectTeamService.addTeamMember(projectTeam);
            response.put("status", "SUCCESS");
            response.put("message", "Team member added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-team-members/{projectId}")
    public ResponseEntity<Map<String, Object>> getTeamMembersByProject(@PathVariable Long projectId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Team members retrieved successfully");
            response.put("data", projectTeamService.getTeamMembersByProjectId(projectId));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/remove-team-member/{projectId}/{employeeId}")
    public ResponseEntity<Map<String, Object>> removeTeamMember(@PathVariable Long projectId, @PathVariable Long employeeId) {
        Map<String, Object> response = new HashMap<>();
        try {
            projectTeamService.removeTeamMember(projectId, employeeId);
            response.put("status", "SUCCESS");
            response.put("message", "Team member removed successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
