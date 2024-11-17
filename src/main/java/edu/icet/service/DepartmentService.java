package edu.icet.service;

import edu.icet.dto.Department;
import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    Department updateDepartment(Long id, Department department);
    void deactivateDepartment(Long id);
}
