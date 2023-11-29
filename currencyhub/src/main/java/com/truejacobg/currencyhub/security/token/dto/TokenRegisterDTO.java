package com.truejacobg.currencyhub.security.token.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class TokenRegisterDTO {
    public String name;
    public String authCode;
}
