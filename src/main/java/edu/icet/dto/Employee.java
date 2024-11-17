package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String employeeId;
    private String employeeName;
    private String role;
    private String position;
    private String contactNumber;
    private String address;
    private String city;
    private String gender;
    private String marriedStatus;
    private String status;
    private String joinDate;
    private String departmentId;

    private String email;
    private String password;
}