package edu.icet.repository;

import edu.icet.entity.SkillAssignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SkillAssignRepository extends JpaRepository<SkillAssignEntity, Long> {
    Collection<Object> findByEmployee_EmployeeId(Long employeeId);
}
