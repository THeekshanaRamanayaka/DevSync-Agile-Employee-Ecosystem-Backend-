package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "performance_ratings")
public class PerformanceRatingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    @Column(name = "technical_skill", nullable = false)
    private String technicalSkill;

    @Column(name = "communication", nullable = false)
    private String communication;

    @Column(name = "teamwork", nullable = false)
    private String teamwork;

    @Column(name = "productivity", nullable = false)
    private String productivity;

    @Column(name = "initiative", nullable = false)
    private String initiative;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "performance_id", nullable = false)
    private PerformanceEntity performance;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}
