package com.truejacobg.currencyhub.security.jwt;

import com.truejacobg.currencyhub.security.dto.UserTokenDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth")
@RestController
public class TokenController {

    @Autowired
    public final JWTEncoder encoder = new JWTEncoder();

    @PostMapping("/")
    ResponseEntity<String> createToken(@RequestBody UserTokenDTO userTokenDTO) {

        String token = encoder.GenerateJWT(userTokenDTO);

        return ResponseEntity.ok(token);
    }
}
