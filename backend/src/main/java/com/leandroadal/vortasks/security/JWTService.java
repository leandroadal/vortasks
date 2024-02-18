package com.leandroadal.vortasks.security;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTService {

    @Value("${api.security.token.secret}")
	private String secret;

    @Value("${api.security.token.expiration}")
    private long expirationInMilliseconds;

    @Autowired
    private TokenCache tokenCache;
    
    public String generateToken(UserSS user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("vortasks")
                    .withSubject(user.getUsername())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new JWTException("Erro ao gerar o token", e);
        }
    }

    public String validateToken(String token){
        if (tokenCache.isTokenRevoked(token)) {
            throw new JWTException("Token revogado!");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("vortasks")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new JWTException("Erro ao verificar o token!", exception);
        }
    }

    private Instant genExpirationDate(){
        return Instant.now().plusMillis(expirationInMilliseconds);
    }
}
