package edu.icet.service;

import edu.icet.dto.SkillAssign;

import java.util.List;
import java.util.Optional;

public interface SkillAssignService {
    void assignSkillToEmployee(SkillAssign skillAssign);
    void updateSkillAssignment(Long skillAssignId, SkillAssign skillAssign);
    Optional<SkillAssign> getSkillAssignmentById(Long skillAssignId);
    List<SkillAssign> getAllSkillAssignments();
    List<SkillAssign> getSkillAssignmentsByEmployeeId(Long employeeId);
    void deleteSkillAssignment(Long skillAssignId);
}
