package com.truejacobg.currencyhub.user.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// reprezentacja danych z bazy, każde pole jest oddzielną kolumną, nie pokazujemy userowi, nie powinno wejsc do kontrolera

@Document("user") // zeby baza danych wiedziala jak przemapowac obiekt
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data // ma w sobie potwierdzenie ze wszystko jest ustawione poprawnie
public class UserEntity {
    @Id
    private String id;
    private String firstName;
    private String name;
    private String authCode;
    private String surname;
    private String email;
    private LocalDateTime creationDate;
}
