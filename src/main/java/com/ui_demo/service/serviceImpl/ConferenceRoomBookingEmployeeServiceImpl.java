package com.ui_demo.service.serviceImpl;

import com.ui_demo.dto.employee.EmployeeResponseDto;
import com.ui_demo.service.ConferenceRoomBookingEmployeeService;

import java.util.List;

public class ConferenceRoomBookingEmployeeServiceImpl implements ConferenceRoomBookingEmployeeService {
    @Override
    public List<EmployeeResponseDto> getEmployees() {
        return List.of();
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        return null;
    }

    @Override
    public List<EmployeeResponseDto> searchEmployees(String name) {
        return List.of();
    }
}
