package com.pnc.clientinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

/**
 * ClientInfo encapsulates client IP address in a Spring-managed bean.
 * Injected into CustomFilter, it holds and shares client IP data effectively and
 * follows Spring's design patterns for dependency management. Adhering to the
 * principles of inversion of control and encapsulating the management of the
 * ClientInfo state within the Spring framework.
 */
@Component
public class ClientInfo {
    private String clientIpAddress;

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }
}
