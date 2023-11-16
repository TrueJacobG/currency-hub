package com.truejacobg.currencyhub.security;

import com.truejacobg.currencyhub.exception.AuthorizationException;
import com.truejacobg.currencyhub.security.dto.NoAuthForFilterDTO;
import com.truejacobg.currencyhub.security.jwt.Authentication;
import com.truejacobg.currencyhub.security.jwt.JWTDecoder;
import com.truejacobg.currencyhub.user.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
@EnableConfigurationProperties()
@Component
public class AuthenticationFilter implements Filter {

    private static Logger logger = LogManager.getLogger(AuthenticationFilter.class.getName());

    private final UserService userService;
    private final JWTDecoder jwtDecoder = new JWTDecoder();

    private final Encoder encoder;

    private final NoAuthForFilterDTO noAuthUris;
    private final String blockedPath = "/api/v1/user/";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean needToBeChecked = true;

        for (Map.Entry<String, String> entry : noAuthUris.request.entrySet())
        {
            if(request.getRequestURI().startsWith(entry.getValue()) &&(request.getMethod().equals(entry.getKey().toUpperCase().split("\\.")[0])) )
            {
                logger.info("NIE FILTRUJ " + request.getMethod());
                needToBeChecked=false;
            }
        }

        if (needToBeChecked) {
            String token = request.getHeader("Authorization");

            Authentication authentication = jwtDecoder.tokenToAuthentication(token);
            authentication.setPassword(encoder.encode(authentication.getPassword()));

            String password = encoder.encode(userService.getUserPasswordByName(authentication.getName()));

            if (authentication.getPassword().equals(password)) {

                logger.info(String.format("User [%s] has been authenticated", authentication.getName()));
            } else {
                logger.warn(String.format("User [%s] has NOT been authenticated", authentication.getName()));
                throw new AuthorizationException("Authorization fail! Wrong name or password!");
            }
        }

        filterChain.doFilter(request, servletResponse);
    }
}
