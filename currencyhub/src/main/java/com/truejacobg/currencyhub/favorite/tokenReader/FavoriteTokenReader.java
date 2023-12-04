package com.truejacobg.currencyhub.favorite.tokenReader;


import com.truejacobg.currencyhub.security.dto.Authentication;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.truejacobg.currencyhub.security.JWTDecoder;

@Component
public class FavoriteTokenReader {

    private final ServletRequest servletRequest;

    public FavoriteTokenReader(@Qualifier("httpServletRequest") ServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public Authentication getNameFromToken(HttpServletRequest servletRequest) {
        final JWTDecoder jwtDecoder = new JWTDecoder();

        String token = servletRequest.getHeader("Authorization");

        return jwtDecoder.tokenToAuthentication(servletRequest.getHeader("Authorization"));
    }

}
