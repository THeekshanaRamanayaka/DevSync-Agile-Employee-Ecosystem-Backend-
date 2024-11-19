package edu.icet.service;

import edu.icet.dto.Employee;
import java.util.List;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
