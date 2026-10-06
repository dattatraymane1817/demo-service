package com.ui_demo.service.serviceImpl;

import com.ui_demo.dto.employee.EmployeeLoginDto;
import com.ui_demo.dto.employee.EmployeeRegistrationDto;
import com.ui_demo.dto.employee.EmployeeResponseDto;
import com.ui_demo.entity.Employee;
import com.ui_demo.enums.EmployeeRole;
import com.ui_demo.repository.EmployeeRepo;
import com.ui_demo.service.EmployeeLoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeLoginServiceImpl implements EmployeeLoginService {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    public EmployeeLoginServiceImpl(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public EmployeeResponseDto login(EmployeeLoginDto request) {

        Employee employee = employeeRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return toResponse(employee);
    }

    @Override
    public EmployeeResponseDto register(EmployeeRegistrationDto request) {
        if(employeeRepo.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        Employee employee=new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setRole(request.getRole()!=null?request.getRole():EmployeeRole.EMPLOYEE);

        Employee savedEmployee=employeeRepo.save(employee);

        return EmployeeResponseDto.builder()
                .id(savedEmployee.getId())
                .name(savedEmployee.getName())
                .email(savedEmployee.getEmail())
                .role(savedEmployee.getRole())
                .build();
    }

    private EmployeeResponseDto toResponse(Employee employee) {

        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .role(employee.getRole())
                .build();
    }
}