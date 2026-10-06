package com.ui_demo.dto.employee;

import lombok.Getter;

@Getter
public class ErrorResponseDto {
    private final int status;
    private final String message;

    public ErrorResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

}