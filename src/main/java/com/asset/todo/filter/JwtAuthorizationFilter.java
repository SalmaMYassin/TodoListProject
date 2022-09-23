package com.asset.todo.filter;

import com.asset.todo.security.JwtUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/todo/login")||request.getServletPath().equals("/todo/token/refresh"))
            filterChain.doFilter(request, response);
        else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (JwtUtilities.verifyAuthorizationHeader(authorizationHeader)) {
                try {
                    String token = JwtUtilities.getTokenFromHeader(authorizationHeader);
                    JwtUtilities.addUserTokenInSecurityContext(JwtUtilities.verifyAndDecodeJWT(token));
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    log.error("Error logging in: {}", e.getMessage());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                    response.sendError(FORBIDDEN.value(), e.getMessage());
                }
            } else
                filterChain.doFilter(request, response);
        }
    }
}
