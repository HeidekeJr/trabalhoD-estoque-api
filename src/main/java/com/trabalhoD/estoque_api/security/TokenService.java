package com.trabalhoD.estoque_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    private static final String SECRET = "trabalho-estoque-api";

    public String gerarToken(String login){

        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.create().withSubject(login).withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS)).sign(algorithm);
    }

    public String validToken(String token){

        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.require(algorithm).build().verify(token).getSubject();
    }
}
