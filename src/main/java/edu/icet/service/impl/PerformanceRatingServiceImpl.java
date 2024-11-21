package edu.icet.service.impl;

import edu.icet.dto.PerformanceRating;
import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.PerformanceEntity;
import edu.icet.entity.PerformanceRatingsEntity;
import edu.icet.repository.EmployeeRepository;
import edu.icet.repository.PerformanceRatingsRepository;
import edu.icet.repository.PerformanceRepository;
import edu.icet.service.PerformanceRatingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformanceRatingServiceImpl implements PerformanceRatingService {
    private final PerformanceRatingsRepository performanceRatingsRepository;
    private final PerformanceRepository performanceRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    @Override
    public PerformanceRating createPerformanceRating(PerformanceRating performanceRating) {
        PerformanceEntity performance = performanceRepository.findById(Long.parseLong(performanceRating.getPerformanceId()))
                .orElseThrow(() -> new EntityNotFoundException("Performance not found"));

        EmployeeEntity employee = employeeRepository.findById(performanceRating.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        PerformanceRatingsEntity performanceRatingsEntity = modelMapper.map(performanceRating, PerformanceRatingsEntity.class);
        performanceRatingsEntity.setPerformance(performance);
        performanceRatingsEntity.setEmployee(employee);

        PerformanceRatingsEntity savedPerformanceRating = performanceRatingsRepository.save(performanceRatingsEntity);
        return modelMapper.map(savedPerformanceRating, PerformanceRating.class);
    }

    @Override
    public PerformanceRating updatePerformanceRating(Long ratingId, PerformanceRating performanceRating) {
        PerformanceRatingsEntity existingPerformanceRating = performanceRatingsRepository.findById(ratingId)
                .orElseThrow(() -> new EntityNotFoundException("Performance Rating not found"));

        existingPerformanceRating.setTechnicalSkill(performanceRating.getTechnicalSkill());
        existingPerformanceRating.setCommunication(performanceRating.getCommunication());
        existingPerformanceRating.setTeamwork(performanceRating.getTeamwork());
        existingPerformanceRating.setProductivity(performanceRating.getProductivity());
        existingPerformanceRating.setInitiative(performanceRating.getInitiative());

        if (!existingPerformanceRating.getPerformance().getPerformanceId().equals(Long.parseLong(performanceRating.getPerformanceId()))) {
            PerformanceEntity performance = performanceRepository.findById(Long.parseLong(performanceRating.getPerformanceId()))
                    .orElseThrow(() -> new EntityNotFoundException("Performance not found"));
            existingPerformanceRating.setPerformance(performance);
        }

        if (!existingPerformanceRating.getEmployee().getEmployeeId().equals(performanceRating.getEmployeeId())) {
            EmployeeEntity employee = employeeRepository.findById(performanceRating.getEmployeeId())
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
            existingPerformanceRating.setEmployee(employee);
        }

        PerformanceRatingsEntity updatedPerformanceRating = performanceRatingsRepository.save(existingPerformanceRating);
        return modelMapper.map(updatedPerformanceRating, PerformanceRating.class);
    }

    @Override
    public Optional<PerformanceRating> getPerformanceRatingById(Long ratingId) {
        return performanceRatingsRepository.findById(ratingId)
                .map(performanceRatingsEntity -> modelMapper.map(performanceRatingsEntity, PerformanceRating.class));
    }

    @Override
    public List<PerformanceRating> getAllPerformanceRatings() {
        return performanceRatingsRepository.findAll().stream()
                .map(performanceRatingsEntity -> modelMapper.map(performanceRatingsEntity, PerformanceRating.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PerformanceRating> getPerformanceRatingsByEmployeeId(Long employeeId) {
        return performanceRatingsRepository.findByEmployee_EmployeeIdNative(employeeId).stream()
                .map(performanceRatingsEntity -> modelMapper.map(performanceRatingsEntity, PerformanceRating.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PerformanceRating> getPerformanceRatingsByPerformanceId(Long performanceId) {
        return performanceRatingsRepository.findByPerformance_PerformanceIdNative(performanceId).stream()
                .map(performanceRatingsEntity -> modelMapper.map(performanceRatingsEntity, PerformanceRating.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePerformanceRating(Long ratingId) {
        if (!performanceRatingsRepository.existsById(ratingId)) {
            throw new EntityNotFoundException("Performance Rating not found");
        }
        performanceRatingsRepository.deleteById(ratingId);
    }
}
