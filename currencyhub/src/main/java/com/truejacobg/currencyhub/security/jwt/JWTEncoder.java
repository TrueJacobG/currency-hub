package com.truejacobg.currencyhub.security.jwt;

import com.truejacobg.currencyhub.security.dto.UserTokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Date;

@Configuration
public class JWTEncoder {
    @Value(value = "${encoder.secret-key}")
    String secretKey;

    public String GenerateJWT(UserTokenDTO userTokenDTO) {
        String jwt = Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(Date.from(Instant.now()))
                .claim("name", userTokenDTO.name)
                .claim("authCode", userTokenDTO.authCode)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return jwt;
    }
}
