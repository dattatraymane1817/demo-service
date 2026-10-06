package com.ui_demo.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String s) {
        super(s);
    }
}
