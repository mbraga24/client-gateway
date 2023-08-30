package com.pnc.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
//    -	When all validation is passed, return a random uuid and a welcome message
//    with his username and his City Name (resolved using ip-geolocation api)
    private String token;
}
