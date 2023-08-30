package com.pnc.auth;

import com.pnc.clientinfo.ClientInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final ClientInfo clientInfo;

    @PostMapping("/register")
    public Mono<ResponseEntity<AuthenticationResponse>> register(@RequestBody RegisterRequest request) {
        log.info("clientInfo :: ============> {}", clientInfo.getClientIpAddress());
//        return ResponseEntity.ok(authService.register(request, clientInfo.getClientIpAddress()));

        return authService.register(request, clientInfo.getClientIpAddress()).map(ResponseEntity::ok);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
