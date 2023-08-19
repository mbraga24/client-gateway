package com.pnc.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;
import java.util.function.Function;

@Service
public class JwtService {

    // *Generate new key and move it to application.properties*
    private static final String SECRET_KEY = "5cbd389af4f3e3a38cbc115e5d44a9dae1684f2c0ffa4552df623de3c8a40baa";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver(claims);
    }

    /**
     * https://stackoverflow.com/questions/54339794/how-to-get-claims-value-from-jwt-token-authentication
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
