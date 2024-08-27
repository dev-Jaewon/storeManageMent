package com.won.StoreManageMent.auth.service;

import org.springframework.stereotype.Service;

import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.Playload;
import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.ResponseAuthToken;
import com.won.StoreManageMent.common.jwt.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {

    public final JwtProvider jwtProvider;
    
    @Override
    public ResponseAuthToken createToken(Playload jwtPlayload){

        try {
                
                String accessToken = jwtProvider.createToken(jwtPlayload, 5);
                String refreshToken = jwtProvider.createToken(jwtPlayload, 60);

                return new ResponseAuthToken(accessToken, refreshToken);
                
        } catch (Exception e) {
            return new ResponseAuthToken();
        }

    }

}
