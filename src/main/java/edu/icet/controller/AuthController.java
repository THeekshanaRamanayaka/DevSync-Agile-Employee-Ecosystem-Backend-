package edu.icet.controller;

import edu.icet.dto.Employee;
import edu.icet.dto.Login;
import edu.icet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Employee> getLoginDetails(@RequestBody Login login) {
        System.out.println(login);
        Employee employee = authService.authorizeLogin(login.getEmail(), login.getPassword());
        if(employee != null){
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
