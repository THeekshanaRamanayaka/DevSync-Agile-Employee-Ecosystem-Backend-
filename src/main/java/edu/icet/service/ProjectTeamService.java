package edu.icet.service;

import edu.icet.dto.ProjectTeam;

import java.util.List;

public interface ProjectTeamService {
    void addTeamMember(ProjectTeam projectTeam);
    List<ProjectTeam> getTeamMembersByProjectId(Long projectId);
    void removeTeamMember(Long projectId, Long employeeId);
}
