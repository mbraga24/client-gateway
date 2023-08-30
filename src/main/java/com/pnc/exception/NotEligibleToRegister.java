package com.pnc.exception;

public class NotEligibleToRegister extends RuntimeException {

    public NotEligibleToRegister(String message) {
        super(message);
    }

}
