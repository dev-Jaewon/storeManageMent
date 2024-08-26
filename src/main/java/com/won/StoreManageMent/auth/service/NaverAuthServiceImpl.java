package com.won.StoreManageMent.auth.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.auth.dto.NaverAuthDto;
import com.won.StoreManageMent.auth.dto.PlatFormInfoDto;
import com.won.StoreManageMent.auth.entity.AccountEntity;
import com.won.StoreManageMent.auth.entity.PlatformInfoEntity;
import com.won.StoreManageMent.auth.repository.AccountRepository;
import com.won.StoreManageMent.auth.repository.PlatFormInfoRepository;

@Service
public class NaverAuthServiceImpl implements NaverAuthService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PlatFormInfoRepository platFormInfoRepository;

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

    @Override
    public void insertAuthKeyInfo(PlatFormInfoDto.InsertFloatFormAuthKey insertFloatFormAuthKey){

        AccountEntity account = accountRepository.findById(insertFloatFormAuthKey.getAccountId())
                .orElseThrow(null);

        if (account == null){
            return;
        }

        PlatformInfoEntity platFormInfo = PlatformInfoEntity.builder()
            .accountId(insertFloatFormAuthKey.getAccountId())
            .option1(insertFloatFormAuthKey.getOption1())
            .option2(insertFloatFormAuthKey.getOption2())
            .build();

    }
}
