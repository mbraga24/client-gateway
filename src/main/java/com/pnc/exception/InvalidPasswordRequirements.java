package com.pnc.exception;

public class InvalidPasswordRequirements extends RuntimeException {

    public InvalidPasswordRequirements(String message) {
        super(message);
    }
}
