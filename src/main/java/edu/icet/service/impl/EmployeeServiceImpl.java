package edu.icet.service.impl;

import edu.icet.dto.Employee;
import edu.icet.entity.DepartmentEntity;
import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.LoginEntity;
import edu.icet.exception.ResourceNotFoundException;
import edu.icet.repository.DepartmentRepository;
import edu.icet.repository.EmployeeRepository;
import edu.icet.repository.LoginRepository;
import edu.icet.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final LoginRepository loginRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveEmployee(Employee employee) {
        EmployeeEntity employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
        employeeEntity.setJoinDate(LocalDate.parse(employee.getJoinDate()));

        if (employee.getDepartmentId() != null) {
            DepartmentEntity department = departmentRepository.findById(employee.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));
            employeeEntity.setDepartmentEntity(department);
        }
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

        if (employee.getEmployeeId() != null) {
            LoginEntity loginEntity = new LoginEntity();
            loginEntity.setEmail(employee.getEmail());
            loginEntity.setPassword(employee.getPassword());
            loginEntity.setEmployeeEntity(savedEmployee);
            loginRepository.save(loginEntity);
        }

        modelMapper.map(savedEmployee, Employee.class);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        List<EmployeeEntity> all = employeeRepository.findAll();
        all.forEach(employeeEntity -> employeeList.add(modelMapper.map(employeeEntity, Employee.class)));
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return modelMapper.map(employeeEntity, Employee.class);
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

//        if (!existingEmployee.getEmail().equals(employee.getEmail()) &&
//                employeeRepository.existsByEmail(employee.getEmail())) {
//            throw new RuntimeException("Employee already exists with email: " + employee.getEmail());
//        }

        employee.setEmployeeId(id);
        employee.setJoinDate(String.valueOf(existingEmployee.getJoinDate()));  // Preserve original join date

        EmployeeEntity employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
        EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
        modelMapper.map(updatedEmployee, Employee.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.setStatus("Inactive");

        loginRepository.findById(id).ifPresent(loginRepository::save);
        employeeRepository.save(employee);
    }
}
