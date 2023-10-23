package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
}


// miejsce na rzeczy do bazy, pobieramy, repo ogólnie
// rzadko co sie tu dopisuje, są gotowe metody