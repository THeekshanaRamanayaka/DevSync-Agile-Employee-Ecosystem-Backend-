package edu.icet.service;

import edu.icet.dto.Performance;

import java.util.List;
import java.util.Optional;

public interface PerformanceService {
    Performance createPerformanceReview(Performance performance);
    Performance updatePerformanceReview(Long performanceId, Performance performance);
    Optional<Performance> getPerformanceReviewById(Long performanceId);
    List<Performance> getAllPerformanceReviews();
    void deletePerformanceReview(Long performanceId);
}
