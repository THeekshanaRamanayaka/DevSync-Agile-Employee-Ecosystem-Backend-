package edu.icet.service;

import edu.icet.dto.Department;
import java.util.List;

public interface DepartmentService {
    void saveDepartment(Department department);
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    void updateDepartment(Long id, Department department);
    void deactivateDepartment(Long id);
}
