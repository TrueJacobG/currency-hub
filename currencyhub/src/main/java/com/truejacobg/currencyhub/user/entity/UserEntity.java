package com.truejacobg.currencyhub.user.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// reprezentacja danych z bazy, każde pole jest oddzielną kolumną, nie pokazujemy userowi, nie powinno wejsc do kontrolera

@Document("user") // zeby baza danych wiedziala jak przemapowac obiekt
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data // ma w sobie potwierdzenie ze wszystko jest ustawione poprawnie
public class UserEntity {
    @Id
    private  String id;
    private  String firstName;
    private  String name;
    private  String authCode;
    private  String surname;
    private  String email;
    private  LocalDateTime creationDate;

}
