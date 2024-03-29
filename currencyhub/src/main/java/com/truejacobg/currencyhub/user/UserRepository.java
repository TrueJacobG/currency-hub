package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.OpaqueTokenDsl;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String userEmail);

    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> deleteByEmail(String email);
    Optional<UserEntity> findByNameOrEmail(String Name,String Email);



}

