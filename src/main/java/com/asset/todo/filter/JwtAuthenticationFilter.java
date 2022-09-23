package com.asset.todo.filter;

import com.asset.todo.security.JwtUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String authorizationHeader = new String(Base64.decodeBase64(request.getHeader(HttpHeaders.AUTHORIZATION).substring(6)));
        String username = authorizationHeader.split(":")[0];
        String password = authorizationHeader.split(":")[1];

        log.info("Authenticating username: " + username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();

//        response.setHeader("authorization", JwtUtilities.generateAccessToken(user.getUsername(),request.getRequestURL().toString()));
//        response.setHeader("refresh_token", JwtUtilities.generateRefreshToken(user.getUsername(),request.getRequestURL().toString()));

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", JwtUtilities.generateAccessToken(user.getUsername(), request.getRequestURL().toString()));
        tokens.put("refresh_token", JwtUtilities.generateRefreshToken(user.getUsername(), request.getRequestURL().toString()));
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
