package edu.icet.service.impl;

import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.LoginEntity;
import edu.icet.exception.ResourceNotFoundException;
import edu.icet.repository.EmployeeRepository;
import edu.icet.repository.LoginRepository;
import edu.icet.service.EmployeeService;
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

    private final EmployeeRepository employeeRepository;
    private final LoginRepository loginRepository;
    private final ModelMapper modelMapper;

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeEntity employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
        employeeEntity.setJoinDate(LocalDate.parse(employee.getJoinDate()));

        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

        if (employee.getEmployeeId() != null) {
            LoginEntity loginEntity = new LoginEntity();
            loginEntity.setEmail(employee.getEmail());
            loginEntity.setPassword(employee.getPassword());
            loginEntity.setEmployeeEntity(savedEmployee);
            loginRepository.save(loginEntity);
        }

        return modelMapper.map(savedEmployee, Employee.class);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        List<EmployeeEntity> all = employeeRepository.findAll();
        all.forEach(employeeEntity -> employeeList.add(modelMapper.map(employeeEntity, Employee.class)));
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(String id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return modelMapper.map(employeeEntity, Employee.class);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employeeRepository.findById(employee.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employee.getEmployeeId()));

        EmployeeEntity employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
        employeeEntity.setJoinDate(LocalDate.parse(employee.getJoinDate()));

        EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEmployee, Employee.class);
    }

    @Override
    public void deleteEmployee(String id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.setStatus("Inactive");

        loginRepository.findById(id).ifPresent(loginRepository::save);
        employeeRepository.save(employee);
    }
}
