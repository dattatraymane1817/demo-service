package com.ui_demo.exception;

import jakarta.ws.rs.core.Response;

public class BookingException extends RuntimeException {
    public BookingException(String message) {
        super(message);
    }
}
