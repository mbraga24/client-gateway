package com.clienthub.gateway.auth;

import com.clienthub.gateway.config.JwtService;
import com.clienthub.gateway.config.PasswordValidationService;
import com.clienthub.gateway.exception.custom.InvalidPasswordRequirementsException;
import com.clienthub.gateway.exception.custom.UserExistsException;
import com.clienthub.gateway.exception.custom.UserNotFoundException;
import com.clienthub.gateway.ipapi.IPApiResponse;
import com.clienthub.gateway.ipapi.IPApiService;
import com.clienthub.gateway.user.Role;
import com.clienthub.gateway.user.User;
import com.clienthub.gateway.user.UserRepository;
import com.clienthub.gateway.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    @Value("${app.geolocation.enabled}")
    private boolean geolocationEnabled;

    public AuthenticationResponse register(RegisterRequest request, String userIpAddress) {

        if (geolocationEnabled) {
            IPApiResponse response = ipApiService.ipAPICall(userIpAddress);
            validationUtils.isAuthorizeToRegister(response.getCountry());
        }

        validateIfUserExists(request.getEmail());
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

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("The user %s could not be found".formatted(request.getEmail())));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void validatePassword(String password) {
        if (!passwordValidationService.isPasswordValid(password)) {
            throw new InvalidPasswordRequirementsException("Invalid password requirements.");
        }
    }

    private void validateIfUserExists(String userEmail) {
        if (userRepository.existsByEmail(userEmail)) {
            throw new UserExistsException("This email is already taken.");
        }
    }

}
