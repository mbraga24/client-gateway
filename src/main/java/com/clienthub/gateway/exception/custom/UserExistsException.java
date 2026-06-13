package com.clienthub.gateway.exception.custom;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String message) {
        super(message);
    }

}
