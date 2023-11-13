package com.truejacobg.currencyhub.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.truejacobg.currencyhub.exception.GlobalException;
import com.truejacobg.currencyhub.security.jwt.Authentication;
import com.truejacobg.currencyhub.security.jwt.JWTDecoder;
import com.truejacobg.currencyhub.user.UserRepository;
import com.truejacobg.currencyhub.user.UserService;
import com.truejacobg.currencyhub.user.entity.UserEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@AllArgsConstructor
@Component
public class AuthenticationFilter implements Filter {

    private static Logger logger = LogManager.getLogger(AuthenticationFilter.class.getName());

    private final UserService userService;
    private final JWTDecoder jwtDecoder = new JWTDecoder();
    private final Encoder encoder = new Encoder();
    private final String blockedPath = "/api/v1/user/";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (!(request.getRequestURI().startsWith(blockedPath) &&
                (request.getMethod().equals("GET") || request.getMethod().equals("POST")))) {
            String token = request.getHeader("Authorization");

            Authentication authentication = jwtDecoder.tokenToAuthentication(token);
            authentication.setPassword(encoder.endoce(authentication.getPassword()));

            String password = encoder.endoce(userService.getUserPasswordByName(authentication.getName()));

            if (authentication.getPassword().equals(password)) {

                logger.info("SOMTEHING IS HAPPENING");
            } else {
                throw new GlobalException("Authorization fail! Wrong email or password!");
            }
        }

        filterChain.doFilter(request, servletResponse);
    }
}
