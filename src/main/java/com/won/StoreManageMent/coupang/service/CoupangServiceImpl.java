package com.won.StoreManageMent.coupang.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coupang.openapi.sdk.Hmac;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.coupang.dto.CoupangDto;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CoupangServiceImpl implements CoupangService {

    private final HttpClient httpClient;
    
    private final String URL = "https://api-gateway.coupang.com";
    
    @Value("${coupang.vender_id}")
    private String VENDER_ID;

    @Value("${coupang.secret_key}")
    private String SECRET_KEY;

    @Value("${coupang.access_key}")
    private String ACCESS_KEY;


    @Override
    public CompletableFuture<ArrayList<CoupangDto.OrderData>> getOrders(String status){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeDate = now.minus(31, ChronoUnit.DAYS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String method = "GET";
        String path = "/v2/providers/openapi/apis/api/v4/vendors/" + VENDER_ID +
                      "/ordersheets?createdAtFrom=" + beforeDate.format(formatter) +
                      "&createdAtTo=" + now.format(formatter) +
                      "&status=" + status;

        try {

            String authorization = getAuth(method, path);

            HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(URL + path))
                        .header("Authorization", authorization)
                        .header("content-type", "application/json;charset=UTF-8")
                        .GET()
                        .build();

            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(response -> {
                            try {
                                
                                ObjectMapper objectMapper = new ObjectMapper();
                                CoupangDto.OrderInfo orderInfo = objectMapper.readValue(response.body(), CoupangDto.OrderInfo.class);
                                
                                return orderInfo.getData();
                            } catch (JsonProcessingException e) {
                                return new ArrayList<CoupangDto.OrderData>();
                            }
                        })
                        .exceptionally(e -> {
                            return new ArrayList<>();
                        });
            
        } catch (RuntimeException | URISyntaxException e) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }
    }

    private String getAuth(String method, String url){
        return Hmac.generate(method, url, SECRET_KEY, ACCESS_KEY);
    }

}