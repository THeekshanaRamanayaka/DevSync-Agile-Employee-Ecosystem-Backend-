package edu.icet.repository;

import edu.icet.entity.PerformanceRatingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PerformanceRatingsRepository extends JpaRepository<PerformanceRatingsEntity, Long> {
//    List<PerformanceRatingsEntity> findByEmployee_EmployeeId(Long employeeId);
    @Query(value = "SELECT * FROM performance_ratings WHERE employee_id = :employeeId", nativeQuery = true)
    Collection<PerformanceRatingsEntity> findByEmployee_EmployeeIdNative(@Param("employeeId") Long employeeId);

//    List<PerformanceRatingsEntity> findByPerformance_PerformanceId(Long performanceId);
    @Query(value = "SELECT * FROM performance_ratings WHERE performance_id = :performanceId", nativeQuery = true)
    Collection<PerformanceRatingsEntity> findByPerformance_PerformanceIdNative(@Param("performanceId") Long performanceId);
}
