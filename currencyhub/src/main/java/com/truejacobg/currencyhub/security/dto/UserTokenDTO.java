package com.truejacobg.currencyhub.security.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class UserTokenDTO {
    public String name;
    public String authCode;
}
