package com.ui_demo.exception;

import com.ui_demo.dto.employee.ErrorResponseDto;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponseDto(500, "Something went wrong. Please try again."))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}