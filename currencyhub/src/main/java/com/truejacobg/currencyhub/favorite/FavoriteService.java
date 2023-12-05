package com.truejacobg.currencyhub.favorite;

import com.truejacobg.currencyhub.exception.UserFavoriteCurrencyException;
import com.truejacobg.currencyhub.favorite.dto.CreateFavoriteResponseDTO;
import com.truejacobg.currencyhub.favorite.dto.DeleteFavoriteResponseDTO;
import com.truejacobg.currencyhub.favorite.dto.GetFavoriteResponseDTO;
import com.truejacobg.currencyhub.favorite.entity.FavoriteEntity;
import com.truejacobg.currencyhub.security.JWTDecoder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;


    public CreateFavoriteResponseDTO addFavoriteCurrency(String currencyCode, HttpServletRequest servletRequest) {

        JWTDecoder jwtDecoder = new JWTDecoder(servletRequest);
        String userName = jwtDecoder.getNameFromToken(servletRequest);

        if (favoriteRepository.findByNameAndCurrencyCode(userName, currencyCode).isPresent()) {
            throw new UserFavoriteCurrencyException("this currency is already in database", HttpStatus.FOUND);
        } else {
            FavoriteEntity favoriteEntity = new FavoriteEntity(currencyCode, userName, LocalDate.now());
            favoriteRepository.save(favoriteEntity);
        }
        return new CreateFavoriteResponseDTO("Favorite currency added successfully.", HttpStatus.OK);
    }

    public DeleteFavoriteResponseDTO deleteFavoriteCurrency(String currencyCode, HttpServletRequest servletRequest) {

        JWTDecoder jwtDecoder = new JWTDecoder(servletRequest);
        String userName = jwtDecoder.getNameFromToken(servletRequest);

        if (favoriteRepository.findByNameAndCurrencyCode(userName, currencyCode).isPresent()) {
            favoriteRepository.deleteByNameAndCurrencyCode(userName, currencyCode);
        } else {
            throw new UserFavoriteCurrencyException("currency has not been found", HttpStatus.NOT_FOUND);
        }
        return new DeleteFavoriteResponseDTO("currency has been deleted from favorites", HttpStatus.OK);
    }

    public GetFavoriteResponseDTO getFavoriteCurrency(HttpServletRequest servletRequest) {


        JWTDecoder jwtDecoder = new JWTDecoder(servletRequest);
        String userName = jwtDecoder.getNameFromToken(servletRequest);

        if (favoriteRepository.findFavoriteCurrencyByName(userName) != null) {

            List<FavoriteEntity> favoriteCurrencies = new ArrayList<>();

            favoriteCurrencies.addAll(favoriteRepository.findByName(userName));
            return new GetFavoriteResponseDTO("gotcha ur currency boi", favoriteCurrencies, HttpStatus.OK);
        } else {
            throw new UserFavoriteCurrencyException("currency has not been found", HttpStatus.NOT_FOUND);
        }
    }


}
