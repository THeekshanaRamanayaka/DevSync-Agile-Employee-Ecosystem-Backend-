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
public class Department {
    private String departmentId;
    private String departmentName;
    private Integer headcount;
    private String departmentHead;
    private String status;
    private LocalDate creationDate;
}