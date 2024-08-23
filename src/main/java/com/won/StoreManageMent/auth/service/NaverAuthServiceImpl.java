package com.won.StoreManageMent.auth.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.auth.dto.NaverAuthDto;

@Service
public class NaverAuthServiceImpl implements NaverAuthService {

    @Override
    public NaverAuthDto naverLogin(String token){
        try{
        
            String NAVER_AUTH_API = "https://openapi.naver.com/v1/nid/me";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(NAVER_AUTH_API))
                        .header("Authorization", "Bearer " + token)
                        .header("content-type", "application/json")
                        .GET()
                        .build();

            String res = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            ObjectMapper objectMapper = new ObjectMapper();
            NaverAuthDto naverAuthDto = objectMapper.readValue(res, NaverAuthDto.class);

            return naverAuthDto;

        }catch(URISyntaxException | IOException | InterruptedException e){
            System.out.println(e);
            return new NaverAuthDto();
        }
    }
}
