package edu.icet.service;

import edu.icet.dto.Employee;
import edu.icet.dto.Login;

public interface AuthService {
    Employee authorizeLogin(String email, String password);
}
