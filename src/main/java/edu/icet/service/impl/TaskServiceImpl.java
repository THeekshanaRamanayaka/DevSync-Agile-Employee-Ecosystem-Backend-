package edu.icet.service.impl;

import edu.icet.dto.Task;
import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.ProjectEntity;
import edu.icet.entity.TaskEntity;
import edu.icet.repository.EmployeeRepository;
import edu.icet.repository.ProjectRepository;
import edu.icet.repository.TaskRepository;
import edu.icet.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public Task createTask(Task task) {
        ProjectEntity project = projectRepository.findById(task.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        EmployeeEntity employee = employeeRepository.findById(task.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        TaskEntity taskEntity = modelMapper.map(task, TaskEntity.class);
        taskEntity.setProject(project);
        taskEntity.setEmployee(employee);

        TaskEntity savedTask = taskRepository.save(taskEntity);
        return modelMapper.map(savedTask, Task.class);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        TaskEntity existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        existingTask.setTaskDescription(task.getTaskDescription());
        existingTask.setTaskStatus(task.getTaskStatus());
        existingTask.setAssignDate(task.getAssignDate());

        if (!existingTask.getProject().getProjectId().equals(task.getProjectId())) {
            ProjectEntity project = projectRepository.findById(task.getProjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Project not found"));
            existingTask.setProject(project);
        }

        if (!existingTask.getEmployee().getEmployeeId().equals(task.getEmployeeId())) {
            EmployeeEntity employee = employeeRepository.findById(task.getEmployeeId())
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
            existingTask.setEmployee(employee);
        }

        TaskEntity updatedTask = taskRepository.save(existingTask);
        return modelMapper.map(updatedTask, Task.class);
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .map(taskEntity -> modelMapper.map(taskEntity, Task.class));
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        List<TaskEntity> taskEntities = taskRepository.findAll();
        taskEntities.forEach(taskEntity -> taskList.add(modelMapper.map(taskEntity, Task.class)));
        return taskList;
    }

    @Override
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new EntityNotFoundException("Task not found");
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findAll().stream()
                .filter(taskEntity -> taskEntity.getProject().getProjectId().equals(projectId))
                .map(taskEntity -> modelMapper.map(taskEntity, Task.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksByEmployeeId(Long employeeId) {
        return taskRepository.findAll().stream()
                .filter(taskEntity -> taskEntity.getEmployee().getEmployeeId().equals(employeeId))
                .map(taskEntity -> modelMapper.map(taskEntity, Task.class))
                .collect(Collectors.toList());
    }
}
