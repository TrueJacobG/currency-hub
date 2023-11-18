package com.truejacobg.currencyhub.security.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class UriDTO {
    public String path;
    public String method;
}
