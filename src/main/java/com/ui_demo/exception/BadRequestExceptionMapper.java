package com.ui_demo.exception;

import com.ui_demo.dto.employee.ErrorResponseDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponseDto(400, e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}