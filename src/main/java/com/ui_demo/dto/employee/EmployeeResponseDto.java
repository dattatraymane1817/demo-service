package com.ui_demo.dto.employee;

import com.ui_demo.enums.EmployeeRole;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String email;
    private EmployeeRole role;
}