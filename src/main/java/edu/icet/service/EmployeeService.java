package edu.icet.service;

import edu.icet.dto.Employee;
import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(String id);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(String id);
}
