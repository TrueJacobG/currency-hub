package com.truejacobg.currencyhub.favorite;

import com.truejacobg.currencyhub.currency.CurrencyRepository;
import com.truejacobg.currencyhub.currency.entity.CurrencyEntity;
import com.truejacobg.currencyhub.exception.WrongCurrencyCodeException;
import com.truejacobg.currencyhub.favorite.dto.CreateFavoriteResponseDTO;
import com.truejacobg.currencyhub.favorite.dto.DeleteFavoriteResponseDTO;
import com.truejacobg.currencyhub.favorite.dto.FavoriteDTO;
import com.truejacobg.currencyhub.favorite.dto.GetFavoriteResponseDTO;
import com.truejacobg.currencyhub.favorite.entity.FavoriteEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepository;
    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private HttpServletRequest servletRequest;

    @InjectMocks
    private FavoriteService favoriteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void prepareService() {
        favoriteService = new FavoriteService(favoriteRepository, currencyRepository);
    }

    @Test
    void addFavoriteCurrency() {
        // given
        String currencyCode = "USD";
        String currencyCodeWrong = "USDxD";
        String userId = "testUser";
        LocalDate creationDate = LocalDate.now();

        FavoriteEntity favoriteEntity = new FavoriteEntity();
        favoriteEntity.setCurrencyCode(currencyCode);
        favoriteEntity.setName(userId);
        favoriteEntity.setDate(creationDate);

        given(favoriteRepository.save(favoriteEntity)).willReturn(favoriteEntity);
        given(favoriteRepository.findByNameAndCurrencyCode(userId,currencyCode)).willReturn(Optional.of(favoriteEntity));

        // when

        when(servletRequest.getHeader("Authorization")).thenReturn("Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiS3ViYVBsYXllcjEiLCJwYXNzd29yZCI6IlRlc3RIYXNsbzEyMyJ9.3IpA7dpOe30YxhQ4EBgC2EmRnnzkdciF7R-Q4TaYVxA");
        when(favoriteRepository.findByNameAndCurrencyCode(userId, currencyCode)).thenReturn(Optional.empty());

        // Then
        CreateFavoriteResponseDTO responseDTO = favoriteService.addFavoriteCurrency("EUR",servletRequest);

        assertEquals("Favorite currency added successfully.", responseDTO.getMessage());
        assertEquals(HttpStatus.OK, responseDTO.getStatus());

        try {
            CreateFavoriteResponseDTO responseWrong = favoriteService.addFavoriteCurrency(currencyCodeWrong, servletRequest);
        }catch (WrongCurrencyCodeException e){
            assertEquals("Currency not found in the database",e.getMessage());
        }



    }

    @Test
    void deleteFavoriteCurrency() {

        // Given
        String currencyCode = "USD";
        String userId = "testUser";
        LocalDate creationDate = LocalDate.now();

        FavoriteEntity favoriteEntity = new FavoriteEntity();
        favoriteEntity.setCurrencyCode(currencyCode);
        favoriteEntity.setName(userId);
        favoriteEntity.setDate(creationDate);

        given(favoriteRepository.save(favoriteEntity)).willReturn(favoriteEntity);
        given(favoriteRepository.findByNameAndCurrencyCode(userId,currencyCode)).willReturn(Optional.of(favoriteEntity));


        when(servletRequest.getHeader("Authorization")).thenReturn("Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiS3ViYVBsYXllcjEiLCJwYXNzd29yZCI6IlRlc3RIYXNsbzEyMyJ9.3IpA7dpOe30YxhQ4EBgC2EmRnnzkdciF7R-Q4TaYVxA");
        when(favoriteRepository.findByNameAndCurrencyCode(userId, currencyCode)).thenReturn(Optional.of(new FavoriteEntity()));

        // When
        DeleteFavoriteResponseDTO response = favoriteService.deleteFavoriteCurrency(currencyCode, servletRequest);

        // Then
        assertEquals("currency has been deleted from favorites", response.getMessage());
        assertEquals(HttpStatus.OK, response.getStatus());



    }

    @Test
    void getFavoriteCurrency() {
        String userName = "testUser";
        LocalDate currentDate = LocalDate.now();
        List<FavoriteEntity>mockFavoriteEntities = new ArrayList<>();
        mockFavoriteEntities.add(new FavoriteEntity("EUR", "testUser", currentDate));

        when(servletRequest.getHeader("Authorization")).thenReturn("Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiS3ViYVBsYXllcjEiLCJwYXNzd29yZCI6IlRlc3RIYXNsbzEyMyJ9.3IpA7dpOe30YxhQ4EBgC2EmRnnzkdciF7R-Q4TaYVxA");
        when(favoriteRepository.findFavoriteCurrencyByName(userName)).thenReturn(mockFavoriteEntities);


        GetFavoriteResponseDTO response = favoriteService.getFavoriteCurrency(servletRequest);

        assertEquals("gotcha ur currency boi", response.getMessage());
        assertEquals(HttpStatus.OK, response.getStatus());




    }
}