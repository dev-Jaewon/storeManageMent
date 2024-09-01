package com.won.StoreManageMent.naver.service;

import java.util.ArrayList;
import java.util.Base64;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.naver.dto.NaverPayLoad;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NaverServiceImpl implements NaverService{

    @Value("${naver.clientId}")
    private String CLIENT_ID;
    
    @Value("${naver.clientSecretKey}")
    private String CLIENT_SECRET_KEY;

    private final int IMAGE_SIZE = 1000;

    private final HttpClient httpClient;

    @Override
    public String newAuthToken(){

        try {
      
            long timeStamp = System.currentTimeMillis();
            String password = CLIENT_ID + "_" + timeStamp;

            String hashed = BCrypt.hashpw(password, CLIENT_SECRET_KEY);

            String clientSecretSign = Base64.getEncoder().encodeToString(hashed.getBytes(StandardCharsets.UTF_8));

            NaverPayLoad payLoad = NaverPayLoad.builder()
                .client_id(CLIENT_ID)
                .timestamp(timeStamp)
                .grant_type("client_credentials")
                .client_secret_sign(clientSecretSign)
                .type("SELF")
                .build();

            String payLoadToString = new ObjectMapper().writeValueAsString(payLoad)
                    .replace("{","")
                    .replace("}","")
                    .replace(":","=")
                    .replace("\"","")
                    .replace(",","&");

            String AUTH_API = "https://api.commerce.naver.com/external/v1/oauth2/token";

            HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(AUTH_API))
                        .header("content-type", "application/x-www-form-urlencoded")
                        .POST(HttpRequest.BodyPublishers.ofString(payLoadToString))
                        .build();

            String res = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();

            System.out.println(res);


            return res;

        } catch(Exception e){

            return null;
        }

    }

}
