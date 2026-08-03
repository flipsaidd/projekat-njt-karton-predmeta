package com.fgc.kartonpredmeta.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    public String generateToken(String username, String uloga) {
        Algorithm algorithm=Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withSubject(username)
                .withClaim("uloga", uloga)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    public DecodedJWT validateTokenAndGetDecoded(String token){
        JWTVerifier verifier=JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        return verifier.verify(token);
    }
}
