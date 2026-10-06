package com.ui_demo.dto.employee;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeLoginDto {

    private String email;
    private String password;
}