package com.pnc.exception.custom;

public class NotEligibleToRegisterException extends RuntimeException {

    public NotEligibleToRegisterException(String message) {
        super(message);
    }

}
