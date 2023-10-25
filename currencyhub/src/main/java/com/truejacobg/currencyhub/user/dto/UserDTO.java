package com.truejacobg.currencyhub.user.dto;

import lombok.*;

import java.time.LocalDateTime;

// są to rzeczy które przychodzą lub odsyłamy naszym user'om
@AllArgsConstructor // mogą przyjść puste rzeczy,
@NoArgsConstructor
@Setter // nie zbędniki w mongo
@Getter // nie zbędniki w mongo
@Data
public class UserDTO {
    // post tworzyć
    private String name;
    private String surname;
    private String authCode;
    private String email;
    private LocalDateTime creationDate;
}
