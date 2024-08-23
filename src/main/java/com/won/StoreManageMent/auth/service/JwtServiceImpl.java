package com.won.StoreManageMent.auth.service;

import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.Playload;

@Service
public class JwtServiceImpl implements JwtService {

    private final String SECRET_KEY = "포트폴리용 임시 시크릿키";
    private final String ISSUER = "won";
    
    @Override
    public String createToken(Playload jwtPlayload){

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, String> payload = objectMapper.convertValue(jwtPlayload, new TypeReference<Map<String, String>>(){});

            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            String token = JWT.create()
                .withPayload(payload)
                .withIssuer(ISSUER)
                .sign(algorithm);
            
            return token;

        } catch (RuntimeErrorException exception){
            return null;
        }
    }

    @Override
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
