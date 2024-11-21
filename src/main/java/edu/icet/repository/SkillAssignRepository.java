package edu.icet.repository;

import edu.icet.entity.SkillAssignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SkillAssignRepository extends JpaRepository<SkillAssignEntity, Long> {
//    Collection<Object> findByEmployee_EmployeeId(Long employeeId);

    @Query(value = "SELECT * FROM skill_assign WHERE employee_id = :employeeId", nativeQuery = true)
    Collection<SkillAssignEntity> findByEmployee_EmployeeIdNative(@Param("employeeId") Long employeeId);
}
