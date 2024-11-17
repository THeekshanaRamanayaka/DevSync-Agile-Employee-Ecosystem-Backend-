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
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "employee_role", nullable = false)
    private String role;

    @Column(name = "employee_position", nullable = false)
    private String position;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "married_status", nullable = false)
    private String marriedStatus;

    @Column(name = "employee_status", nullable = false)
    private String status;

    @Column(name = "join_date", nullable = false, updatable = false)
    private LocalDate joinDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;
}