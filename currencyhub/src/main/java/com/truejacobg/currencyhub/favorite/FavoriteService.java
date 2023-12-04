package com.truejacobg.currencyhub.favorite;

import com.truejacobg.currencyhub.exception.UserFavoriteCurrencyException;
import com.truejacobg.currencyhub.favorite.dto.*;
import com.truejacobg.currencyhub.favorite.entity.FavoriteEntity;
import com.truejacobg.currencyhub.security.JWTDecoder;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;


    private final JWTDecoder jwtDecoder = new JWTDecoder();

    public FavoriteResponseDTO addFavoriteCurrency(String currencyCode, String token) {
        String userName = jwtDecoder.tokenToAuthentication(token).getName();
        if (favoriteRepository.findByNameAndCurrencyCode(userName, currencyCode).isPresent()) {
            throw new UserFavoriteCurrencyException("this currency is already in database", HttpStatus.FOUND);
        } else {

            FavoriteEntity favoriteEntity = new FavoriteEntity(currencyCode, userName, LocalDateTime.now().toString());
            favoriteRepository.save(favoriteEntity);

        }
        return new FavoriteResponseDTO("Favorite currency added successfully.", HttpStatus.OK);
    }

    public DeleteFavoriteResponseDTO deleteFavoriteCurrency(String currencyCode, String token) {

        String userName = jwtDecoder.tokenToAuthentication(token).getName();

        if (favoriteRepository.findByNameAndCurrencyCode(userName, currencyCode).isPresent()) {
            favoriteRepository.deleteByNameAndCurrencyCode(userName, currencyCode);
        } else {
            throw new UserFavoriteCurrencyException("currency has not been found", HttpStatus.NOT_FOUND);
        }
        return new DeleteFavoriteResponseDTO("currency has been deleted from favorites", HttpStatus.OK);
    }

    public GetFavoriteResponseDTO getFavoriteCurrency(String token) {


        String userName = jwtDecoder.tokenToAuthentication(token).getName();
        if (favoriteRepository.findFavoriteCurrencyByName(userName) != null) {

            List<FavoriteEntity> favoriteCurrencies = new ArrayList<>();

            for (FavoriteEntity dupadupa : favoriteRepository.findByName(userName)) {
                favoriteCurrencies.add(dupadupa);
            }

            return new GetFavoriteResponseDTO("gotcha ur currency boi", favoriteCurrencies, HttpStatus.OK);

        } else {
            throw new UserFavoriteCurrencyException("currency has not been found", HttpStatus.NOT_FOUND);
        }
    }
}
