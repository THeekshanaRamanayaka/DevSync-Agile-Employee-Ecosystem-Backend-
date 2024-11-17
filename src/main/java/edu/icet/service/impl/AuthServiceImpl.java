package edu.icet.service.impl;

import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.LoginEntity;
import edu.icet.repository.AuthRepository;
import edu.icet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final ModelMapper modelMapper;

    @Override
    public Employee authorizeLogin(String email, String password) {
        LoginEntity loginEntity = authRepository.findByEmail(email);
        System.out.println(loginEntity);
        EmployeeEntity employeeEntity = loginEntity.getEmployeeEntity();

        boolean isValidPassword = Objects.equals(password, loginEntity.getPassword());
        if (isValidPassword) {
            return modelMapper.map(employeeEntity, Employee.class);
        }
        return null;
    }
}
