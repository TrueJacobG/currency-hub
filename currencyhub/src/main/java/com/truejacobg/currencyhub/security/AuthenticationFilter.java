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
    //TODO:: this encoder uses random salt each time .encode is invoked. check for alternatives
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //TODO:: salt should be in custonEncoder
    private final String salt = "$2a$10$EUoCY87J.YJD6F4foMJGouI.NrQ1l2hvOoKhdNvi/wwlgtY7433N.";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader("Authorization");
        Authentication authentication = jwtDecoder.tokenToAuthentication(token);

        authentication.setPassword(BCrypt.hashpw(authentication.getPassword(),salt));

        String password = BCrypt.hashpw(userService.getUserPasswordByName(authentication.getName()),salt);

        //check if user password is equal to users password in database
        if (authentication.getPassword().equals(password)) {
            filterChain.doFilter(request, servletResponse);
        } else{
           // throw new GlobalException("Authorization fail! Wrong email or password!"); //TODO:throw when not valid
        }
    }
}
