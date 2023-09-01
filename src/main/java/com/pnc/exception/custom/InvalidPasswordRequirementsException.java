package com.pnc.exception.custom;

public class InvalidPasswordRequirementsException extends RuntimeException {

    public InvalidPasswordRequirementsException(String message) {
        super(message);
    }
}
