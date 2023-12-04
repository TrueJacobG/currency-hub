package com.truejacobg.currencyhub.favorite;

import com.truejacobg.currencyhub.favorite.dto.DeleteFavoriteResponseDTO;
import com.truejacobg.currencyhub.favorite.dto.FavoriteResponseDTO;
import com.truejacobg.currencyhub.favorite.dto.GetFavoriteResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
@RequestMapping(value = "/api/v1/favorite")
@RestController
public class FavoriteController {

    private FavoriteService favoriteService;


    @PostMapping("/favorite/{currencyCode}")
    ResponseEntity<FavoriteResponseDTO> addFavoriteCurrency(@PathVariable String currencyCode, @RequestHeader("Authorization") String token) {
        FavoriteResponseDTO favoriteResponseDTO = favoriteService.addFavoriteCurrency(currencyCode, token);

        return ResponseEntity.ok(favoriteResponseDTO);
    }

    @DeleteMapping("/favorite/{currencyCode}")
    ResponseEntity<DeleteFavoriteResponseDTO> deleteFavoriteCurrency(@PathVariable String currencyCode, @RequestHeader("Authorization") String token) {
        DeleteFavoriteResponseDTO deleteFavoriteResponseDTO = favoriteService.deleteFavoriteCurrency(currencyCode, token);

        return ResponseEntity.ok(deleteFavoriteResponseDTO);
    }

    @GetMapping("/favorite")
    ResponseEntity<GetFavoriteResponseDTO> getFavoriteCurrency(@RequestHeader("Authorization") String token) {
        GetFavoriteResponseDTO getFavoriteResponseDTO = favoriteService.getFavoriteCurrency(token);

        return ResponseEntity.ok(getFavoriteResponseDTO);
    }


}
