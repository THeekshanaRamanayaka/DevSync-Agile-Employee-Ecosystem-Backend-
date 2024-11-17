package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceRating {
    private String ratingId;
    private String technicalSkill;
    private String communication;
    private String teamwork;
    private String productivity;
    private String initiative;
    private LocalDate updatedAt;
    private String performanceId;
    private String employeeId;
}