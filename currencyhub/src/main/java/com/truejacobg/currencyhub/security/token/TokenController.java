package com.truejacobg.currencyhub.security.token;

import com.truejacobg.currencyhub.security.token.dto.TokenLoginDTO;
import com.truejacobg.currencyhub.security.token.dto.TokenRegisterDTO;
import com.truejacobg.currencyhub.security.token.dto.TokenResponse;
import com.truejacobg.currencyhub.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth")
@RestController
public class TokenController {
    private JWTEncoder encoder;
    private UserService userService;

    @PostMapping("/register")
    ResponseEntity<TokenResponse> createToken(@RequestBody TokenRegisterDTO tokenDTO) {
        String token = encoder.generateJWT(tokenDTO);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/login")
    ResponseEntity<TokenResponse> getToken(@RequestBody TokenLoginDTO tokenDTO) {
        String name = userService.getUser(tokenDTO.getEmail()).getUser().getName();
        String token = encoder.generateJWT(new TokenRegisterDTO(name, tokenDTO.getAuthCode()));
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
