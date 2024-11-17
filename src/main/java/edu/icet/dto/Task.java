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
public class Task {
    private Long taskId;
    private String taskDescription;
    private String taskStatus;
    private LocalDate assignDate;
    private Long projectId;
    private Long employeeId;
}
