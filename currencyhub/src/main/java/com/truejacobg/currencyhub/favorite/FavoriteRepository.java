package com.truejacobg.currencyhub.favorite;

import com.truejacobg.currencyhub.favorite.entity.FavoriteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends MongoRepository<FavoriteEntity, String> {
    Optional<FavoriteEntity> findByNameAndCurrencyCode(String name, String currencyCode);

    Optional<FavoriteEntity> deleteByNameAndCurrencyCode(String name, String currencyCode);

    List<FavoriteEntity> findFavoriteCurrencyByName(String name);

    List<FavoriteEntity> findByName(String name);
}
