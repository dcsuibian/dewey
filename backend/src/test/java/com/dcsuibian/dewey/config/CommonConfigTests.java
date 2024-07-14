package com.dcsuibian.dewey.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommonConfigTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder() {
        String hashedPassword = passwordEncoder.encode("password");
        assertEquals(60, hashedPassword.length());
        assertTrue(passwordEncoder.matches("password", hashedPassword));
        assertFalse(passwordEncoder.matches("password1", hashedPassword));
    }
}
