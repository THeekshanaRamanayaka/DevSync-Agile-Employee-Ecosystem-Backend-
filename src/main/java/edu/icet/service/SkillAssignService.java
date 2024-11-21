package edu.icet.service;

import edu.icet.dto.SkillAssign;

import java.util.List;
import java.util.Optional;

public interface SkillAssignService {
    SkillAssign assignSkillToEmployee(SkillAssign skillAssign);
    SkillAssign updateSkillAssignment(Long skillAssignId, SkillAssign skillAssign);
    Optional<SkillAssign> getSkillAssignmentById(Long skillAssignId);
    List<SkillAssign> getAllSkillAssignments();
    List<SkillAssign> getSkillAssignmentsByEmployeeId(Long employeeId);
    void deleteSkillAssignment(Long skillAssignId);
}
