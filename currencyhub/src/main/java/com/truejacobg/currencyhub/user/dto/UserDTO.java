package com.truejacobg.currencyhub.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class UserDTO {
    @Size(min = 3, max = 50, message = "name must be between 2 and 50 characters")
    @NotBlank
    private String name;

    @Size(min = 3, max = 50, message = "firstName must be between 3 and 50 characters")
    @NotBlank
    private String firstname;

    @Size(min = 2, max = 50, message = "surname must be between 2 and 50 characters")
    @NotBlank
    private String surname;

    @Size(min = 10, max = 20, message = "password must be between 10 and 20 characters")
    @NotBlank
    private String authCode;

    @Size(min = 5, max = 50, message = "email must be between 5 and 50 characters")
    @Email(message = "Email should be valid")
    @NotBlank
    private String email;

    private LocalDateTime creationDate;
}


