package com.won.StoreManageMent.coupang.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coupang.openapi.sdk.Hmac;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.coupang.dto.CoupangDto;


@Service
public class CoupangServiceImpl implements CoupangService {
    
    private final String URL = "https://api-gateway.coupang.com";
    
    @Value("${coupang.vender_id}")
    private String VENDER_ID;

    @Value("${coupang.secret_key}")
    private String SECRET_KEY;

    @Value("${coupang.access_key}")
    private String ACCESS_KEY;
    



    @Override
    public CoupangDto.OrderInfo getOrders(){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeDate = now.minus(31, ChronoUnit.DAYS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String method = "GET";
        String path = "/v2/providers/openapi/apis/api/v4/vendors/" + VENDER_ID +
                      "/ordersheets?createdAtFrom=" + beforeDate.format(formatter) +
                      "&createdAtTo=" + now.format(formatter) +
                      "&status=" + "FINAL_DELIVERY";

        try {

            HttpClient client = HttpClient.newHttpClient();

            String authorization = getAuth(method, path);

            HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(URL + path))
                        .header("Authorization", authorization)
                        .header("content-type", "application/json;charset=UTF-8")
                        .GET()
                        .build();

            String res = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            ObjectMapper objectMapper = new ObjectMapper();
            CoupangDto.OrderInfo orderInfo = objectMapper.readValue(res, CoupangDto.OrderInfo.class);

            return orderInfo;
            
        } catch (RuntimeException | URISyntaxException | IOException | InterruptedException e) {
            return new CoupangDto.OrderInfo();
        }
    }

    private String getAuth(String method, String url){
        return Hmac.generate(method, url, SECRET_KEY, ACCESS_KEY);
    }

}