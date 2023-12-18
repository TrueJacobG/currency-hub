package com.truejacobg.currencyhub.wallet;

import com.truejacobg.currencyhub.wallet.entity.WalletCurrencyExchangeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletCurrencyExchangeRepository extends MongoRepository<WalletCurrencyExchangeEntity, String> {
    WalletCurrencyExchangeEntity findByName(String name);
}
