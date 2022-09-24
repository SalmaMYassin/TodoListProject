package com.asset.todo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.nio.charset.StandardCharsets;
import java.util.*;

public final class JwtUtilities {

    private static final String JWT_SECRET = System.getProperty("JWT_SECRET","secret");
    private static final Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    private static final String BEARER = "Bearer ";
    private static final String USER = "USER";

    public static String generateAccessToken(String username, String issuer) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public static String generateRefreshToken(String username, String issuer) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public static DecodedJWT verifyAndDecodeJWT(String token) {
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        return jwtVerifier.verify(token);
    }

    public static void addUserTokenInSecurityContext(DecodedJWT decodedJWT) {
        String username = decodedJWT.getSubject();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public static Boolean verifyAuthorizationHeader(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith(BEARER);
    }

    public static String getTokenFromHeader(String authorizationHeader) {
        return authorizationHeader.substring(BEARER.length());
    }

    public static Collection<SimpleGrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER));
        return authorities;
    }

}
