package edu.icet.repository;

import edu.icet.entity.ProjectTeamEntity;
import edu.icet.entity.ProjectTeamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTeamRepository extends JpaRepository<ProjectTeamEntity, Long> {
    boolean existsById(ProjectTeamId teamId);
    List<ProjectTeamEntity> findByProject_ProjectId(Long projectId);
    void deleteById(ProjectTeamId teamId);
}
