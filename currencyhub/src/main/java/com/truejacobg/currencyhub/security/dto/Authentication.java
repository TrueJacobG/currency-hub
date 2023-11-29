package com.truejacobg.currencyhub.security.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Authentication {
    private String name;
    private String password;
}
