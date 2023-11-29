package com.truejacobg.currencyhub.user.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class GetUserResponseDTO {
    private UserDTO user;
    private String message;
    private HttpStatus status;
}
