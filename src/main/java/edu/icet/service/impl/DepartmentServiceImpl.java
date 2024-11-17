package edu.icet.service.impl;

import edu.icet.dto.Department;
import edu.icet.entity.DepartmentEntity;
import edu.icet.repository.DepartmentRepository;
import edu.icet.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Department saveDepartment(Department department) {
        if (departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
            throw new RuntimeException("Department already exists with name: " + department.getDepartmentName());
        }

        DepartmentEntity departmentEntity = modelMapper.map(department, DepartmentEntity.class);
        departmentEntity.setCreationDate(LocalDate.now());
        departmentEntity.setStatus("Active");

        DepartmentEntity savedEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedEntity, Department.class);
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departmentList = new ArrayList<>();
        departmentRepository.findAll().forEach(departmentEntity -> departmentList.add(modelMapper.map(departmentEntity, Department.class)));
        return departmentList;
    }

    @Override
    public Department getDepartmentById(Long id) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        return modelMapper.map(department, Department.class);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        DepartmentEntity existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        if (!existingDepartment.getDepartmentName().equals(department.getDepartmentName()) &&
                departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
            throw new RuntimeException("Department already exists with name: " + department.getDepartmentName());
        }

        department.setDepartmentId(id);
        department.setCreationDate(existingDepartment.getCreationDate());

        DepartmentEntity departmentEntity = modelMapper.map(department, DepartmentEntity.class);
        DepartmentEntity updatedEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(updatedEntity, Department.class);
    }

    @Override
    public void deactivateDepartment(Long id) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        department.setStatus("Inactive");
        departmentRepository.save(department);
    }
}
