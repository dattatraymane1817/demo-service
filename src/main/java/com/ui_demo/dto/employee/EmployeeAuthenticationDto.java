package com.ui_demo.dto.employee;

import com.ui_demo.enums.EmployeeRole;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeAuthenticationDto {

    private Long employeeId;
    private String email;
    private EmployeeRole role;
    private boolean authenticated;
}