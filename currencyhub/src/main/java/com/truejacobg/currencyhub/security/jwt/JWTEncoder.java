package com.truejacobg.currencyhub.security.jwt;

import com.truejacobg.currencyhub.security.dto.UserTokenDTO;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTEncoder {
    String secretKey = "5B58872AFEC560B0A530751044ECC76E57BEF32EFFE6A56BD8F68A9391F5C9E9";
    public String GenerateJWT(UserTokenDTO userTokenDTO){
        String jwt = Jwts.builder()
                .setHeaderParam("alg","HS256")
                .setIssuedAt(Date.from(Instant.now()))
                .claim("name",userTokenDTO.name)
                .claim("authCode", userTokenDTO.authCode)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return jwt;
    }
}
