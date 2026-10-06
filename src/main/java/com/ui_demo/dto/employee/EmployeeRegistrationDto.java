package com.ui_demo.dto.employee;

import com.ui_demo.enums.EmployeeRole;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRegistrationDto {

    private String name;
    private String email;
    private String password;
    private EmployeeRole role;
}