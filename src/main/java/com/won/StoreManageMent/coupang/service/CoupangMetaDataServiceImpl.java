package com.won.StoreManageMent.coupang.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coupang.openapi.sdk.Hmac;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.coupang.dto.CoupangCategoryDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoupangMetaDataServiceImpl implements CoupangMetaDataService {

    private final HttpClient httpClient;

    private final String URL = "https://api-gateway.coupang.com";

    @Value("${coupang.secret_key}")
    private String SECRET_KEY;

    @Value("${coupang.access_key}")
    private String ACCESS_KEY;
 
    @Override
    public CoupangCategoryDto.CategoryData getCategoryData(String displayCategoryCode){

        String method = "GET";
        String path = "/v2/providers/seller_api/apis/api/v1/marketplace/"+
                    "meta/category-related-metas/display-category-codes/" + displayCategoryCode;

        try {

            String authorization = getAuth(method, path);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL + path))
                    .header("Authorization", authorization)
                    .header("content-type", "application/json;charset=UTF-8")
                    .GET()
                    .build();

            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(res -> {
                        try{
                            CoupangCategoryDto categoryDto = new ObjectMapper().readValue(res.body(), CoupangCategoryDto.class);
                            return categoryDto.getData();
                        }catch(RuntimeException | JsonProcessingException e){
                            System.out.println(e);
                            return new CoupangCategoryDto.CategoryData();
                        }
                    }).join();
            
        } catch (RuntimeException | URISyntaxException e) {
            return new CoupangCategoryDto.CategoryData();
        }
        
    }

    private String getAuth(String method, String url){
        return Hmac.generate(method, url, SECRET_KEY, ACCESS_KEY);
    }
}