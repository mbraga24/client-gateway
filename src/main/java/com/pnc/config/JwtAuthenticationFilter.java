package com.pnc.config;

import com.pnc.clientinfo.ClientInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * The JwtAuthenticationFilter custom filter is executed before Global filters
 * and before the controller processing. Responsible for implementing JWT-based
 * authentication logic.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final ClientInfo clientInfo;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String ipAddresses = request.getHeader("X-Forwarded-For");
        final String jwt;
        final String userEmail;
        final String requestURI = request.getRequestURI().toString();

        String endpoint = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        String clientIpAddress = null;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            if (endpoint.equals("register")) {
                if (ipAddresses == null || ipAddresses.isBlank()) {
                    clientIpAddress = request.getRemoteAddr();
                    clientInfo.setClientIpAddress(clientIpAddress);
                } else {
                    clientIpAddress = ipAddresses.split(",")[0].trim();
                    clientInfo.setClientIpAddress(clientIpAddress);
                }
            }
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        log.info("userEmail: {}", userEmail);
        clientIpAddress = request.getHeader("X-Forwarded-For");

        // if userEmail exists but user is not authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isCanadianIP(String ipAddress) {
        return false;
    }

}
