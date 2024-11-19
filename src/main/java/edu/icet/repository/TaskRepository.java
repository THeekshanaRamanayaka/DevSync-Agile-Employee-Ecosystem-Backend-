package edu.icet.repository;

import edu.icet.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
//     List<TaskEntity> findByTaskStatus(String status);
//     List<TaskEntity> findByEmployee_EmployeeId(Long employeeId);
}