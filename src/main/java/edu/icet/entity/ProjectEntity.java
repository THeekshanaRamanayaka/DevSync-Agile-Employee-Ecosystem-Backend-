package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "project_description", nullable = false)
    private String projectDescription;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "project_client", nullable = false)
    private String client;

    @Column(name = "income", nullable = false)
    private Double income;

    @Column(name = "project_status", nullable = false)
    private String status;

    @Column(name = "starting_date", nullable = false)
    private LocalDate startingDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;
}
