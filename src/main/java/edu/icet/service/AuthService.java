package edu.icet.service;

import edu.icet.dto.Employee;

public interface AuthService {
    Employee authorizeLogin(String email, String password);
}
