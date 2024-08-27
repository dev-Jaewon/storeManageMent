package com.won.StoreManageMent.common.jwt;

import java.util.Date;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.Playload;

@Component
public class JwtProvider {

    @Value("${auth.secretKey}")
    private String SECRET_KEY;

    @Value("${auth.issuer}")
    private String ISSUER;

    private int ONE_MINUTE = 60000; // 1분

    public String createToken(Playload jwtPlayload, int expireTime) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, String> payload = objectMapper.convertValue(jwtPlayload, new TypeReference<Map<String, String>>(){});

            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            String token = JWT.create()
                .withPayload(payload)
                .withIssuer(ISSUER)
                .withIssuedAt(new Date()) 
                .withExpiresAt(new Date(System.currentTimeMillis() + ONE_MINUTE * expireTime))
                .sign(algorithm);
            
            return token;

        } catch (RuntimeErrorException exception){
            return null;
        }

    }

    public String checkToken(String token){
        try{

            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(ISSUER)
            .build();
            
            DecodedJWT decodedJWT = verifier.verify(token);
            
            return decodedJWT.getPayload();
        
        }catch(JWTVerificationException e){
            return null;
        }
    }
}
