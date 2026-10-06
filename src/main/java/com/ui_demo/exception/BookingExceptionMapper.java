package com.ui_demo.exception;

import com.ui_demo.dto.employee.ErrorResponseDto;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BookingExceptionMapper implements ExceptionMapper<BookingException> {

    @Override
    public Response toResponse(BookingException e) {
        return Response.status(Response.Status.CONFLICT)
                .entity(new ErrorResponseDto(409, e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}