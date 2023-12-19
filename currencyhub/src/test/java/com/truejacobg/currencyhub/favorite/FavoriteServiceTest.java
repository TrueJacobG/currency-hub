package com.truejacobg.currencyhub.favorite;

import com.truejacobg.currencyhub.favorite.dto.CreateFavoriteResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepository;

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
        favoriteService = new FavoriteService(favoriteRepository);
    }
    // TODO zrobić dla złych danych jeszcze
    @Test
    void addFavoriteCurrency() {
        // given
        String currencyCode = "USD";
        String userId = "testUser";

        when(servletRequest.getHeader("Authorization")).thenReturn("Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiS3ViYVBsYXllcjEiLCJwYXNzd29yZCI6IlRlc3RIYXNsbzEyMyJ9.3IpA7dpOe30YxhQ4EBgC2EmRnnzkdciF7R-Q4TaYVxA");
        when(favoriteRepository.findByNameAndCurrencyCode(userId, currencyCode)).thenReturn(Optional.empty());

        // When
        CreateFavoriteResponseDTO response = favoriteService.addFavoriteCurrency(currencyCode, servletRequest);

        // Then
        assertEquals("Favorite currency added successfully.", response.getMessage());
        assertEquals(HttpStatus.OK, response.getStatus());

    }

    @Test
    void deleteFavoriteCurrency() {

        // given


    }

    @Test
    void getFavoriteCurrency() {
    }
}