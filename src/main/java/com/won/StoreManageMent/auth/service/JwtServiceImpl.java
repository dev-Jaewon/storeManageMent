package com.won.StoreManageMent.auth.service;

import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.Playload;

@Service
public class JwtServiceImpl implements JwtService {

    private final String SECRET_KEY = "포트폴리용 임시 시크릿키";
    
    @Override
    public String createToken(Playload jwtPlayload){

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, String> payload = objectMapper.convertValue(jwtPlayload, new TypeReference<Map<String, String>>(){});

            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            String token = JWT.create()
                .withPayload(payload)
                .withIssuer("won")
                .sign(algorithm);
            
            return token;

        } catch (RuntimeErrorException exception){
            return null;
        }
    }
}
