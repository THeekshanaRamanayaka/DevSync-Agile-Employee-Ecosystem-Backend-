package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class project {
    private String projectId;
    private String projectName;
    private String projectDescription;
    private String duration;
    private String client;
    private Double income;
    private String status;
    private LocalDate startingDate;
    private LocalDate endDate;
    private String departmentId;
}
