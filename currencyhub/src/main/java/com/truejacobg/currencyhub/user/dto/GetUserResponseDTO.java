package com.truejacobg.currencyhub.user.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor // mogą przyjść puste rzeczy,
@NoArgsConstructor
@Setter // nie zbędniki w mongo
@Getter // nie zbędniki w mongo
@Data
public class GetUserResponseDTO {
    private String message;
    private HttpStatus status;
}
