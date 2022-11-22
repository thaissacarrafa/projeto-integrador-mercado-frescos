package com.meli.projetointegradormelifrescos.untils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.meli.projetointegradormelifrescos.exception.BadCredentialsException;
import com.meli.projetointegradormelifrescos.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;

@Component
public class JWTUtils {
    private static final Long EXPIRATION_TIME = 86_400_000L;
    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${security.secret}")
    private String secret;


    /**
     * Gera um token com base no username passado no body da requisição
     * @param username string
     * @autor Igor S. Fernandes
     * @return String
     */
    public String generateToken(String username){
        String jwt = JWT.create()
                .withSubject(username)
                .withExpiresAt(Date.from(Instant.now().plusMillis(EXPIRATION_TIME)))
                .sign(Algorithm.HMAC256(secret));

        return TOKEN_PREFIX + jwt;
    }

    /**
     * retorna o token decodificado  usando a variavel de ambiente
     * caso o token seja invalido retornará false
     * @param token string
     * @autor Igor S. Fernandes
     * @return Boolean
     */

    public Boolean isValidToken(String token){
        String username = getSubject(token);
        if(username == null || username.isEmpty()) return false;
        return true;
    }

    /**
     * retorna o token decodificado  usando a variavel de ambiente
     * caso o token seja invalido lançará uma exception
     * @trows BadCredentialsException "invalid token"
     * @param jwt string
     * @autor Igor S. Fernandes
     * @return String
     */
    public String getSubject(String jwt){
        try{
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret)).build().verify(jwt);
            return decodedJWT.getSubject();
        }catch ( BadCredentialsException e){
            throw  new BadRequestException("Invalid token");
        }
    }
}
