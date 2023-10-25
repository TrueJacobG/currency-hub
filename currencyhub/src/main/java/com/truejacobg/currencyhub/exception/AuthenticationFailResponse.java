package com.truejacobg.currencyhub.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class AuthenticationFailResponse {
    private String message;
    private HttpStatus status;
}
