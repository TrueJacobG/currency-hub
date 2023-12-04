package com.truejacobg.currencyhub.favorite;

import com.truejacobg.currencyhub.favorite.dto.CreateFavoriteResponseDTO;
import com.truejacobg.currencyhub.favorite.dto.DeleteFavoriteResponseDTO;
import com.truejacobg.currencyhub.favorite.dto.GetFavoriteResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
@RequestMapping(value = "/api/v1/favorite")
@RestController
public class FavoriteController {

    private FavoriteService favoriteService;


    @PostMapping("/{currencyCode}")
    ResponseEntity<CreateFavoriteResponseDTO> addFavoriteCurrency(@PathVariable String currencyCode, HttpServletRequest servletRequest) {
        CreateFavoriteResponseDTO createFavoriteResponseDTO = favoriteService.addFavoriteCurrency(currencyCode, servletRequest);
        System.out.println(servletRequest);
        return ResponseEntity.ok(createFavoriteResponseDTO);
    }

    @DeleteMapping("/{currencyCode}")
    ResponseEntity<DeleteFavoriteResponseDTO> deleteFavoriteCurrency(@PathVariable String currencyCode, HttpServletRequest servletRequest) {
        DeleteFavoriteResponseDTO deleteFavoriteResponseDTO = favoriteService.deleteFavoriteCurrency(currencyCode, servletRequest);

        return ResponseEntity.ok(deleteFavoriteResponseDTO);
    }

    @GetMapping("/")
    ResponseEntity<GetFavoriteResponseDTO> getFavoriteCurrency(HttpServletRequest servletRequest) {
        GetFavoriteResponseDTO getFavoriteResponseDTO = favoriteService.getFavoriteCurrency(servletRequest);

        return ResponseEntity.ok(getFavoriteResponseDTO);
    }


}
