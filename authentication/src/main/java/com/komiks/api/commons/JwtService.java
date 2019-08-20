package com.komiks.api.commons;

import com.komiks.api.infrastructure.db.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;

/**
 * This service aims to handle the generation and validation of the user session..
 */
public class JwtService {

    private final String secret;

    /**
     * Initialize the JWT Service.
     *
     * @param secret the secret signing key
     */
    public JwtService(String secret) {
        this.secret = secret;
    }

    /**
     * Get all claims from the submitted JWT token.
     * 
     * @param token the JWT token.
     * @return the decoded claims.
     */
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(Base64.getEncoder()
            .encodeToString(
                secret.getBytes(StandardCharsets.UTF_8)))
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * Get the username from the JWT token.
     *
     * @param token the JWT token
     * @return the token.
     */
    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    /**
     * Generate token from {@linkplain User}.
     *
     * @param user - {@linkplain User} for creation of token
     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        final LocalDateTime createdLocalDateTime = LocalDateTime.now();
        final LocalDateTime expiryLocalDateTime = createdLocalDateTime.plusMinutes(5);

        final Date createdDate = Date.from(createdLocalDateTime.toInstant(ZoneOffset.UTC));
        final Date expiryDate = Date.from(expiryLocalDateTime.toInstant(ZoneOffset.UTC));

        Key key = new SecretKeySpec(
            secret.getBytes(StandardCharsets.UTF_8),
            SignatureAlgorithm.HS512.getJcaName());
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(createdDate)
            .setExpiration(expiryDate)
            .signWith(key)
            .compact();
    }

    /**
     * Validate the submitted JWT Token.
     *
     * @param token the JWT token.
     * @return true if valid, otherwise false.
     */
    public boolean validateToken(String token) {
        Claims claims = getAllClaimsFromToken(token);

        if (claims != null) {
            return true;
        } else {
            return false;
        }
    }

}
