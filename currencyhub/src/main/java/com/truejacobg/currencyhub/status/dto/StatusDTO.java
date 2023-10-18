package com.truejacobg.currencyhub.status.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class StatusDTO {
    private String message;
    private HttpStatus statusCode;
}
