package com.example.resto.config;

import com.example.resto.user.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {


        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        jwt = authHeader.substring(7);

        var storedtoken = tokenRepository.findByToken(jwt).orElse(null);

        if(storedtoken!=null){
            storedtoken.setExpired(true);
            tokenRepository.save(storedtoken);
            SecurityContextHolder.clearContext();

        }
    }
}
