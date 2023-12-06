package com.truejacobg.currencyhub.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truejacobg.currencyhub.security.dto.Authentication;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JWTDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ServletRequest servletRequest;

    public JWTDecoder(ServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public Authentication tokenToAuthentication(String token) {
        String[] parts = token.split("\\.");

        validToken(parts);

        JSONObject json = new JSONObject(new String(Base64.getUrlDecoder().decode(parts[1])));
        Authentication authentication;

        try {
            authentication = objectMapper.readValue(String.valueOf(json), Authentication.class);
        } catch (JsonProcessingException e) {
            // TODO!
            // correct exception handling
            // for global exception handling
            throw new RuntimeException(e);
        }

        return authentication;
    }

    private void validToken(String[] parts) {
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid Token format");
        }
    }

    public String getNameFromToken(HttpServletRequest servletRequest) {
        final JWTDecoder jwtDecoder = new JWTDecoder(servletRequest);
        String token = servletRequest.getHeader("Authorization");
        Authentication user = jwtDecoder.tokenToAuthentication(token);
        return user.toString();
    }


}