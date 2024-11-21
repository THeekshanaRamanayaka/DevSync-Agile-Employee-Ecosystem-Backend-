package edu.icet.service.impl;

import edu.icet.dto.Performance;
import edu.icet.entity.PerformanceEntity;
import edu.icet.repository.PerformanceRepository;
import edu.icet.service.PerformanceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final ModelMapper modelMapper;

    @Override
    public Performance createPerformanceReview(Performance performance) {
        PerformanceEntity performanceEntity = modelMapper.map(performance, PerformanceEntity.class);
        PerformanceEntity savedPerformance = performanceRepository.save(performanceEntity);
        return modelMapper.map(savedPerformance, Performance.class);
    }

    @Override
    public Performance updatePerformanceReview(Long performanceId, Performance performance) {
        PerformanceEntity existingPerformance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new EntityNotFoundException("Performance review not found"));

        existingPerformance.setReviewDate(performance.getReviewDate());
        existingPerformance.setReviewComment(performance.getReviewComment());
        existingPerformance.setReviewStatus(performance.getReviewStatus());

        PerformanceEntity updatedPerformance = performanceRepository.save(existingPerformance);
        return modelMapper.map(updatedPerformance, Performance.class);
    }

    @Override
    public Optional<Performance> getPerformanceReviewById(Long performanceId) {
        return performanceRepository.findById(performanceId)
                .map(performanceEntity -> modelMapper.map(performanceEntity, Performance.class));
    }

    @Override
    public List<Performance> getAllPerformanceReviews() {
        return performanceRepository.findAll().stream()
                .map(performanceEntity -> modelMapper.map(performanceEntity, Performance.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePerformanceReview(Long performanceId) {
        if (!performanceRepository.existsById(performanceId)) {
            throw new EntityNotFoundException("Performance review not found");
        }
        performanceRepository.deleteById(performanceId);
    }
}
