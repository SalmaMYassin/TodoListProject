package com.asset.todo.controller;

import com.asset.todo.security.JwtUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TokenController {
    //  Refresh access token
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (JwtUtilities.verifyAuthorizationHeader(authorizationHeader)) {
            try {
                String refresh_token = JwtUtilities.getTokenFromHeader(authorizationHeader);
                String username = JwtUtilities.verifyAndDecodeJWT(authorizationHeader).getSubject();

                response.setHeader("authorization", JwtUtilities.generateAccessToken(username,
                        request.getRequestURL().toString()));
                response.setHeader("refresh_token", refresh_token);
            } catch (Exception e) {
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
                response.sendError(FORBIDDEN.value(), e.getMessage());
            }
        }
    }
}
