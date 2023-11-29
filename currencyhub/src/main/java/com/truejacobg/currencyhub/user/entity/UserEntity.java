package com.truejacobg.currencyhub.user.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("user")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
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
