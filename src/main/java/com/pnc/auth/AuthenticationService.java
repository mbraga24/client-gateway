package com.pnc.auth;

import com.pnc.config.JwtService;
import com.pnc.config.PasswordValidationService;
import com.pnc.exception.InvalidPasswordRequirements;
import com.pnc.exception.NotEligibleToRegister;
import com.pnc.ipapi.IPApiResponse;
import com.pnc.ipapi.IPApiService;
import com.pnc.user.Role;
import com.pnc.user.User;
import com.pnc.user.UserRepository;
import com.pnc.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordValidationService passwordValidationService;
    private final IPApiService ipApiService;
    private final ValidationUtils validationUtils;

    public Mono<AuthenticationResponse> register(RegisterRequest request, String userIpAddress) {

        return ipApiService.ipAPICall(userIpAddress).flatMap(response -> {
            validationUtils.isAuthorizeToRegister(response.getCountry());
            validatePassword(request.getPassword());

            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role((Role.USER))
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);

            return Mono.just(AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build());
        });

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void validatePassword(String password) {
        if (!passwordValidationService.isPasswordValid(password)) {
            throw new InvalidPasswordRequirements("Invalid password requirements.");
        }
    }

}
