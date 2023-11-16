package com.truejacobg.currencyhub.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max = 50, message = "About Me must be between 3 and 50 characters")
    @NotBlank
    private String firstName;

    @Size(min = 3, max = 50, message = " About Me must be between 2 and 50 characters")
    @NotBlank
    private String name;

    @Size(min = 2, max = 50, message = "About Me must be between 2 and 50 characters")
    @NotBlank
    private String surname;

    @Size(min = 10, max = 20, message = "About Me must be between 10 and 20 characters")
    @NotBlank
    private String authCode;

    @Size(min = 5, max = 50, message = "About Me must be between 5 and 50 characters")
    @Email(message = "Email should be valid")
    @NotBlank
    private String email;

    private LocalDateTime creationDate;
}


