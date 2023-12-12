package com.truejacobg.currencyhub.security.token;

import com.truejacobg.currencyhub.security.token.dto.TokenRegisterDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Date;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class JWTEncoder {
    @Value(value = "${encoder.secret-key}")
    private String secretKey;

    public String generateJWT(TokenRegisterDTO tokenDTO) {
        String jwt = Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(Date.from(Instant.now()))
                .claim("name", tokenDTO.getName())
                .claim("password", tokenDTO.getAuthCode())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return jwt;
    }
}
