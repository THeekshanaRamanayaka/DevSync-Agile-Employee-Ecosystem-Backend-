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
public class Performance {
    private Long performanceId;
    private LocalDate reviewDate;
    private String reviewComment;
    private String reviewStatus;
    private LocalDate createdAt;
}
