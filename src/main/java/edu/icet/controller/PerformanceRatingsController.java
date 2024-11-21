package edu.icet.controller;

import edu.icet.dto.PerformanceRating;
import edu.icet.service.PerformanceRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/performance-rating")
@RequiredArgsConstructor
public class PerformanceRatingsController {
    private final PerformanceRatingService performanceRatingService;

    @PostMapping("/add-rating")
    public ResponseEntity<Map<String, Object>> createPerformanceRating(@RequestBody PerformanceRating performanceRating) {
        Map<String, Object> response = new HashMap<>();
        try {
            performanceRatingService.createPerformanceRating(performanceRating);
            response.put("status", "SUCCESS");
            response.put("message", "Performance rating created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, Object>> getAllPerformanceRatings() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Performance ratings retrieved successfully");
            response.put("data", performanceRatingService.getAllPerformanceRatings());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("get-rating-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getPerformanceRatingById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            PerformanceRating performanceRating = performanceRatingService.getPerformanceRatingById(id)
                    .orElseThrow(() -> new RuntimeException("Performance rating not found"));
            response.put("status", "SUCCESS");
            response.put("message", "Performance rating retrieved successfully");
            response.put("data", performanceRating);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-rating-by-employeeId/{employeeId}")
    public ResponseEntity<Map<String, Object>> getPerformanceRatingsByEmployeeId(@PathVariable Long employeeId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Performance ratings by employee retrieved successfully");
            response.put("data", performanceRatingService.getPerformanceRatingsByEmployeeId(employeeId));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-rating-by-performanceId/{performanceId}")
    public ResponseEntity<Map<String, Object>> getPerformanceRatingsByPerformanceId(@PathVariable Long performanceId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "SUCCESS");
            response.put("message", "Performance ratings by performance retrieved successfully");
            response.put("data", performanceRatingService.getPerformanceRatingsByPerformanceId(performanceId));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-rating/{id}")
    public ResponseEntity<Map<String, Object>> updatePerformanceRating(@PathVariable Long id, @RequestBody PerformanceRating performanceRating) {
        Map<String, Object> response = new HashMap<>();
        try {
            performanceRatingService.updatePerformanceRating(id, performanceRating);
            response.put("status", "SUCCESS");
            response.put("message", "Performance rating updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete-rating/{id}")
    public ResponseEntity<Map<String, Object>> deletePerformanceRating(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            performanceRatingService.deletePerformanceRating(id);
            response.put("status", "SUCCESS");
            response.put("message", "Performance rating deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
