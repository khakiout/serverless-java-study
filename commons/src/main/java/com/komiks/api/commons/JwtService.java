package com.komiks.api.commons;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private Claims getAllClaimsFromToken(String token) {
        String bearer = "BEARER ";
        if (token != null && token.toUpperCase().startsWith(bearer)) {
            token = token.substring(bearer.length());
        }

        try {
            return Jwts.parser().setSigningKey(Base64.getEncoder()
                .encodeToString(
                    secret.getBytes(StandardCharsets.UTF_8)))
                .parseClaimsJws(token)
                .getBody();
        } catch (SignatureException | DecodingException | ExpiredJwtException
            | MalformedJwtException | IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Get the username from the JWT token.
     *
     * @param token the JWT token
     * @return the token.
     */
    public String getUsernameFromToken(String token) {
        if (validateToken(token)) {
            return getAllClaimsFromToken(token).getSubject();
        }

        return null;
    }

    /**
     * Generate token for the subject.
     *
     * @param username the username.
     * @return the JWT token.
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        final LocalDateTime createdLocalDateTime = LocalDateTime.now();
        final LocalDateTime expiryLocalDateTime = createdLocalDateTime.plusMinutes(5);

        ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(createdLocalDateTime);
        final Date createdDate = Date.from(createdLocalDateTime.toInstant(zoneOffset));
        final Date expiryDate = Date.from(expiryLocalDateTime.toInstant(zoneOffset));

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
