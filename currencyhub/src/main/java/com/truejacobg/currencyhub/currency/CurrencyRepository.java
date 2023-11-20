package com.truejacobg.currencyhub.currency;

import com.truejacobg.currencyhub.currency.entity.CurrencyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends MongoRepository<CurrencyEntity, String> {
    CurrencyEntity findByCurrencyCode(String currencyCode);
}
