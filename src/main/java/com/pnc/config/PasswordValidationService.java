package com.pnc.config;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class PasswordValidationService {
    public boolean isPasswordValid(String password) {
        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[_#$%.]).{8,}$";
        return Pattern.compile(regex).matcher(password).matches();
    }
}