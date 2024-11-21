package edu.icet.controller;

import edu.icet.dto.Performance;
import edu.icet.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/performance")
@RequiredArgsConstructor
public class PerformanceController {
    private final PerformanceService performanceService;

    @PostMapping("/add-performance-review")
    public ResponseEntity<Map<String, Object>> createPerformanceReview(@RequestBody Performance performance) {
        Map<String, Object> response = new HashMap<>();
        try {
            Performance savedPerformance = performanceService.createPerformanceReview(performance);
            response.put("status", "SUCCESS");
            response.put("message", "Performance review created successfully");
            response.put("data", savedPerformance);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, Object>> getAllPerformanceReviews() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Performance> performances = performanceService.getAllPerformanceReviews();
            response.put("status", "SUCCESS");
            response.put("message", "Performance reviews retrieved successfully");
            response.put("data", performances);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/performance-review-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getPerformanceReviewById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Performance performance = performanceService.getPerformanceReviewById(id)
                    .orElseThrow(() -> new RuntimeException("Performance review not found"));
            response.put("status", "SUCCESS");
            response.put("message", "Performance review retrieved successfully");
            response.put("data", performance);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-performance-review/{id}")
    public ResponseEntity<Map<String, Object>> updatePerformanceReview(@PathVariable Long id, @RequestBody Performance performance) {
        Map<String, Object> response = new HashMap<>();
        try {
            Performance updatedPerformance = performanceService.updatePerformanceReview(id, performance);
            response.put("status", "SUCCESS");
            response.put("message", "Performance review updated successfully");
            response.put("data", updatedPerformance);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete-performance-review/{id}")
    public ResponseEntity<Map<String, Object>> deletePerformanceReview(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            performanceService.deletePerformanceReview(id);
            response.put("status", "SUCCESS");
            response.put("message", "Performance review deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
