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
@Table(name = "skill_assign")
public class SkillAssignEntity {
    @Id
    @Column(name = "skill_assign_id")
    private String skillAssignId;

    @Column(name = "proficiency", nullable = false)
    private String proficiency;

    @Column(name = "years_of_experience", nullable = false)
    private String yearsOfExperience;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillEntity skill;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}
