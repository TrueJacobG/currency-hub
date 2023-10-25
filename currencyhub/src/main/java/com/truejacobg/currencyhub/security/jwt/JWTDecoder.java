package com.truejacobg.currencyhub.security.jwt;

import java.io.DataInput;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class JWTDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();

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
}