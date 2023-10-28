package com.truejacobg.currencyhub.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.truejacobg.currencyhub.exception.AuthenticationFailResponse;
import com.truejacobg.currencyhub.security.jwt.Authentication;
import com.truejacobg.currencyhub.security.jwt.JWTDecoder;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationFilter implements Filter {

    private static Logger logger = LogManager.getLogger(AuthenticationFilter.class.getName());

    private final JWTDecoder jwtDecoder = new JWTDecoder();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        String token = request.getHeader("Authorization");
        Authentication authentication = jwtDecoder.tokenToAuthentication(token);

        authentication.setPassword(encoder.encode(authentication.getPassword()));

       // System.out.println(authentication);
       //DEBUG HERE
        logger.info(authentication);
        // check if user with that username and password exists

        // if yes chain
        boolean valid = false;


        if (valid) {
            filterChain.doFilter(request, servletResponse);
        } else {
            // move it to global exception handler

            String message = "Authentication Fail! Name or password is not valid!";
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            AuthenticationFailResponse failResponse = new AuthenticationFailResponse(message, status);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String jsonFailResponse = ow.writeValueAsString(failResponse);

            response.setStatus(status.value());
            response.getWriter().write(jsonFailResponse);
        }
    }
}
