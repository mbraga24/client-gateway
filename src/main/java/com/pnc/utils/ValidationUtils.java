package com.pnc.utils;

import com.pnc.exception.custom.NotEligibleToRegisterException;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    public void isAuthorizeToRegister(String userIpAddress) {
        if (!userIpAddress.equals("Canada")) {
            throw new NotEligibleToRegisterException("We're sorry, this application is only available for users located in Canada. " +
                    "We appreciate your interest and hope to expand to other regions in the future.");
        }
    }

}
