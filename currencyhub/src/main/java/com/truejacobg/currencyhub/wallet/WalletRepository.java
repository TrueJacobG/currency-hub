package com.truejacobg.currencyhub.wallet;

import com.truejacobg.currencyhub.wallet.entity.WalletEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface WalletRepository extends MongoRepository<WalletEntity, String> {
    WalletEntity findWalletEntityByName(String name);
    Optional<WalletEntity> findByName(String name);
}
