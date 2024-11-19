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
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_description", nullable = false)
    private String taskDescription;

    @Column(name = "task_status", nullable = false)
    private String taskStatus;

    @Column(name = "assign_date", nullable = false)
    private LocalDate assignDate;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}
