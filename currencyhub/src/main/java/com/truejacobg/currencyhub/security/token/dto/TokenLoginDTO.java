package com.truejacobg.currencyhub.security.token.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class TokenLoginDTO {
    public String email;
    public String authCode;
}
