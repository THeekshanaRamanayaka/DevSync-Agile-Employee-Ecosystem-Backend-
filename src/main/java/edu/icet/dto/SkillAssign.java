package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillAssign {
    private String skillAssignId;
    private String proficiency;
    private String yearsOfExperience;
    private String skillId;
    private String employeeId;
}
