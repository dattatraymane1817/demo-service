package com.ui_demo.service;


import com.ui_demo.dto.employee.EmployeeResponseDto;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


import java.util.List;

@Path("/api/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ConferenceRoomBookingEmployeeService {

    @GET
    List<EmployeeResponseDto> getEmployees();

    @GET
    @Path("/{id}")
    EmployeeResponseDto getEmployeeById(@PathParam("id") Long id);

    @GET
    @Path("/search")
    List<EmployeeResponseDto> searchEmployees(@QueryParam("name") String name);
}