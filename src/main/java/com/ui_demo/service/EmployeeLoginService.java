package com.ui_demo.service;

import com.ui_demo.dto.employee.EmployeeLoginDto;
import com.ui_demo.dto.employee.EmployeeRegistrationDto;
import com.ui_demo.dto.employee.EmployeeResponseDto;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/employees/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface EmployeeLoginService {
    @POST
    @Path("/login")
    EmployeeResponseDto login(@Valid EmployeeLoginDto request);

    @POST
    @Path("/register")
    EmployeeResponseDto register(EmployeeRegistrationDto request);

//    @GET




}