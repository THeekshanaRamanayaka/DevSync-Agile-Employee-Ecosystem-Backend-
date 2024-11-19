package edu.icet.service.impl;

import edu.icet.dto.Project;
import edu.icet.entity.DepartmentEntity;
import edu.icet.entity.ProjectEntity;
import edu.icet.repository.DepartmentRepository;
import edu.icet.repository.ProjectRepository;
import edu.icet.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveProject(Project project) {
        if (projectRepository.existsByProjectName(project.getProjectName())) {
            throw new RuntimeException("Project already exists with name: " + project.getProjectName());
        }

        DepartmentEntity department = departmentRepository.findById(project.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + project.getDepartmentId()));

        ProjectEntity projectEntity = modelMapper.map(project, ProjectEntity.class);
        projectEntity.setDepartmentEntity(department);
        projectEntity.setStatus("In Progress");

        ProjectEntity savedEntity = projectRepository.save(projectEntity);
        modelMapper.map(savedEntity, Project.class);
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projectList = new ArrayList<>();
        List<ProjectEntity> projectEntities = projectRepository.findAll();
        projectEntities.forEach(projectEntity -> projectList.add(modelMapper.map(projectEntity, Project.class)));
        return projectList;
    }

    @Override
    public Project getProjectById(Long id) {
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        return modelMapper.map(project, Project.class);
    }

    @Override
    public void updateProject(Long id, Project project) {
        ProjectEntity existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        if (!existingProject.getProjectName().equals(project.getProjectName()) &&
                projectRepository.existsByProjectName(project.getProjectName())) {
            throw new RuntimeException("Project already exists with name: " + project.getProjectName());
        }

        DepartmentEntity department = departmentRepository.findById(project.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + project.getDepartmentId()));

        project.setProjectId(id);
        ProjectEntity projectEntity = modelMapper.map(project, ProjectEntity.class);
        projectEntity.setDepartmentEntity(department);

        ProjectEntity updatedEntity = projectRepository.save(projectEntity);
        modelMapper.map(updatedEntity, Project.class);
    }

    @Override
    public void completeProject(Long id) {
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        project.setStatus("Completed");
        projectRepository.save(project);
    }
}
