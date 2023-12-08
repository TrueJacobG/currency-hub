package com.truejacobg.currencyhub.security;

import com.truejacobg.currencyhub.exception.AuthorizationException;
import com.truejacobg.currencyhub.exception.TokenDoesNotExistException;
import com.truejacobg.currencyhub.security.dto.NoAuthForFilterDTO;
import com.truejacobg.currencyhub.security.dto.Authentication;
import com.truejacobg.currencyhub.user.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@AllArgsConstructor
@EnableConfigurationProperties
@Component
@Service
public class AuthenticationFilter implements Filter {

    private static Logger logger = LogManager.getLogger(AuthenticationFilter.class.getName());

    private final UserService userService;
    private ServletRequest servletRequest;
    private final JWTDecoder jwtDecoder = new JWTDecoder(servletRequest);

    private final Encoder encoder;

    private final NoAuthForFilterDTO noAuthUris;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean dontNeedToBeChecked = noAuthUris.request.entrySet().stream().anyMatch(entry -> request.getRequestURI().startsWith(entry.getValue()) && (request.getMethod().equals(entry.getKey().toUpperCase().split("\\.")[0])));

        if (!dontNeedToBeChecked) {
            String token = request.getHeader("Authorization");

            if (token == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is empty.");
                return;
            }

            logger.info(token);

            Authentication authentication = jwtDecoder.tokenToAuthentication(token);

            logger.info(authentication.getPassword());
            logger.info(authentication.getName());

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
