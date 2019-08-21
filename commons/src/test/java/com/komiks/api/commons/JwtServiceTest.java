package com.komiks.api.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class JwtServiceTest {

    private final String secret = "1vRn5BQkuPmpcwUTD8odT4ejZwmSnWMq9CUuW+fA/998FLmjZzrc"
        + "uI1ggbCOQBDbPFzVUuKeB83UiGna8pZ64z5+1CEJonECO0miulkI6Nem2D7v+dE"
        + "3mPVjHjaA5pqXkmOnapZRCkxgFgc0LsmE7yBgt5DiBPI6hNpYytzS3L0+s1Ah0qr"
        + "PekbZHghpyHw1ezTh7E+an1Gh33VIF7EkC2l8zAXLzFftkJGIWs8VAvkF/sMyExDv4"
        + "7p4G+NdXcr8H4rkKMZF9FI=";
    private JwtService jwtService;

    @Before
    public void setUp() {
        jwtService = new JwtService(secret);
    }

    @Test
    public void testValidationOfValidToken() {
        String jwt = jwtService.generateToken("admin");
        System.out.println(jwt);
        String token = "eyJhbGciOiJIUzUxMiJ9."
            + "eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU2NjQyNTkwNSwiZXhwIjoxNTY2NDI2MjA1fQ."
            + "VZPuq5WZAmhXDuyqfoQA9GK5NjfTMCdTcHjOt5tAfsg4YxmUsQF08HGZpF6IFuaPUCOX2Hl"
            + "OsvOpe0FN0DkfnQ";

        String username = jwtService.getUsernameFromToken(token);
        assertEquals("admin", username);
    }

    @Test
    public void testValidationOfExpiredJwtToken() {
        String token = "eyJhbGciOiJIUzUxMiJ9."
            + "eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU2NjMwMzc4MywiZXhwIjoxNTY2Mzg1NzgzfQ."
            + "u_Su8Awe7-Rf9APltbYo0DVBNKbqy1bd5sGOvOT2HCqcbIQ3iQwMXrGGGZGAQyaDpDf"
            + "PZTLfQJWe8uv0fNlwmQ";

        String username = jwtService.getUsernameFromToken(token);
        assertNull(username);
    }

    @Test
    public void testValidationOfGibberishToken() {
        String token = "eyJhbGciOiJIdasdaNjfTMCdTc";

        String username = jwtService.getUsernameFromToken(token);
        assertNull(username);
    }

    @Test
    public void testValidationOfNullToken() {
        String username = jwtService.getUsernameFromToken(null);
        assertNull(username);
    }

    @Test
    public void testValidationOfEmptyToken() {
        String username = jwtService.getUsernameFromToken("");
        assertNull(username);
    }

    @Test
    public void testValidationOfBlankToken() {
        String username = jwtService.getUsernameFromToken("   ");
        assertNull(username);
    }
}
