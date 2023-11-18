package com.truejacobg.currencyhub.security.jwt;

import com.truejacobg.currencyhub.security.dto.UserTokenDTO;
import com.truejacobg.currencyhub.user.dto.CreateUserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;


@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth")
@RestController
public class TokenController {

    public final JWTEncoder encoder = new JWTEncoder();
    @PostMapping("/")
    ResponseEntity<String> createToken(@RequestBody UserTokenDTO userTokenDTO) {

        String token = encoder.GenerateJWT(userTokenDTO);

        return ResponseEntity.ok(token);
    }
}
