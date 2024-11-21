package edu.icet.service.impl;

import edu.icet.dto.ProjectTeam;
import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.ProjectEntity;
import edu.icet.entity.ProjectTeamEntity;
import edu.icet.entity.ProjectTeamId;
import edu.icet.repository.EmployeeRepository;
import edu.icet.repository.ProjectRepository;
import edu.icet.repository.ProjectTeamRepository;
import edu.icet.service.ProjectTeamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTeamServiceImpl implements ProjectTeamService {

    private final ProjectTeamRepository projectTeamRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void addTeamMember(ProjectTeam projectTeam) {
        ProjectEntity project = projectRepository.findById(projectTeam.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectTeam.getProjectId()));

        EmployeeEntity employee = employeeRepository.findById(projectTeam.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + projectTeam.getEmployeeId()));

        ProjectTeamId teamId = new ProjectTeamId(projectTeam.getProjectId(), projectTeam.getEmployeeId());

        if (projectTeamRepository.existsById(teamId)) {
            throw new RuntimeException("Employee is already assigned to this project");
        }

        ProjectTeamEntity teamEntity = new ProjectTeamEntity();
        teamEntity.setId(teamId);
        teamEntity.setProject(project);
        teamEntity.setEmployee(employee);

        ProjectTeamEntity savedEntity = projectTeamRepository.save(teamEntity);
        modelMapper.map(savedEntity, ProjectTeam.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectTeam> getTeamMembersByProjectId(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new RuntimeException("Project not found with id: " + projectId);
        }

        List<ProjectTeam> projectTeams = new ArrayList<>();
        List<ProjectTeamEntity> teamEntities = projectTeamRepository.findByProject_ProjectId(projectId);
        teamEntities.forEach(projectTeamEntity -> projectTeams.add(modelMapper.map(projectTeamEntity, ProjectTeam.class)));
        return projectTeams;
    }

    @Override
    @Transactional
    public void removeTeamMember(Long projectId, Long employeeId) {
        ProjectTeamId teamId = new ProjectTeamId(projectId, employeeId);
        if (!projectTeamRepository.existsById(teamId)) {
            throw new RuntimeException("Team not found");
        }
        projectTeamRepository.deleteById(teamId);
    }
}
