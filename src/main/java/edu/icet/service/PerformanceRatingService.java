package edu.icet.service;

import edu.icet.dto.PerformanceRating;

import java.util.List;
import java.util.Optional;

public interface PerformanceRatingService {
    void createPerformanceRating(PerformanceRating performanceRating);
    void updatePerformanceRating(Long ratingId, PerformanceRating performanceRating);
    Optional<PerformanceRating> getPerformanceRatingById(Long ratingId);
    List<PerformanceRating> getAllPerformanceRatings();
    List<PerformanceRating> getPerformanceRatingsByEmployeeId(Long employeeId);
    List<PerformanceRating> getPerformanceRatingsByPerformanceId(Long performanceId);
    void deletePerformanceRating(Long ratingId);
}
