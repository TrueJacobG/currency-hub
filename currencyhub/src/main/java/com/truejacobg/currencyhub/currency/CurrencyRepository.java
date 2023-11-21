package com.truejacobg.currencyhub.currency;

import com.truejacobg.currencyhub.currency.entity.CurrencyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends MongoRepository<CurrencyEntity, String> {
    Optional<CurrencyEntity> findByCurrencyCode(String currencyCode);

    CurrencyEntity findDataByCurrencyCode(String currencyCode);
}
