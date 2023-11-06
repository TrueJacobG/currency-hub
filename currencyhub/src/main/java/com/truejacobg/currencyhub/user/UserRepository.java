package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByEmail(String userEmail);
    Optional<UserEntity> deleteByEmail(String email);
}


// miejsce na rzeczy do bazy, pobieramy, repo ogólnie
// rzadko co sie tu dopisuje, są gotowe metody