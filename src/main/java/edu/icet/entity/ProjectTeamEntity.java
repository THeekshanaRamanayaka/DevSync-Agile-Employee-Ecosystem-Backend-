package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "project_team")
public class ProjectTeamEntity {
    @EmbeddedId
    private ProjectTeamId id;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
