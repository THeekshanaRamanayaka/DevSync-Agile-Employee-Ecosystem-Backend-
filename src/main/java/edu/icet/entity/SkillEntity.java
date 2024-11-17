package edu.icet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "skill")
public class SkillEntity {
    @Id
    @Column(name = "skill_id")
    private String skillId;

    @Column(name = "skill_name", nullable = false)
    private String skillName;
}
