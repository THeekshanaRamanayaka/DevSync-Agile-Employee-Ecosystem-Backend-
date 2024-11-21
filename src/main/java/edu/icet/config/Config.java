package edu.icet.config;

import edu.icet.dto.ProjectTeam;
import edu.icet.dto.SkillAssign;
import edu.icet.dto.Task;
import edu.icet.entity.ProjectTeamEntity;
import edu.icet.entity.SkillAssignEntity;
import edu.icet.entity.TaskEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hibernate.Hibernate.map;

@Configuration
public class Config {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        Converter<TaskEntity, Task> taskConverter = context -> {
            TaskEntity source = context.getSource();
            Task destination = new Task();

            destination.setTaskId(source.getTaskId());
            destination.setTaskDescription(source.getTaskDescription());
            destination.setTaskStatus(source.getTaskStatus());
            destination.setAssignDate(source.getAssignDate());
            destination.setProjectId(source.getProject().getProjectId());
            destination.setEmployeeId(source.getEmployee().getEmployeeId());

            return destination;
        };

        modelMapper.createTypeMap(TaskEntity.class, Task.class)
                .setConverter(taskConverter);

        modelMapper.createTypeMap(ProjectTeamEntity.class, ProjectTeam.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getId().getProjectId(), ProjectTeam::setProjectId);
                    mapper.map(src -> src.getId().getEmployeeId(), ProjectTeam::setEmployeeId);
                });

        modelMapper.createTypeMap(SkillAssignEntity.class, SkillAssign.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getSkill().getSkillId(), SkillAssign::setSkillId);
                    mapper.map(src -> src.getEmployee().getEmployeeId(), SkillAssign::setEmployeeId);
                });

        return modelMapper;
    }
}
