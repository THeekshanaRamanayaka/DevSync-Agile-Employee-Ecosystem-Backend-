package edu.icet.service;

import edu.icet.dto.Project;

import java.util.List;

public interface ProjectService {
    void saveProject(Project project);
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    void updateProject(Long id, Project project);
    void completeProject(Long id);
}
