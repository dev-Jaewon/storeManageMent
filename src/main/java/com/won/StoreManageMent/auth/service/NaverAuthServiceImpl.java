package com.won.StoreManageMent.auth.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.auth.dto.NaverAuthDto;
import com.won.StoreManageMent.auth.dto.PlatFormInfoDto;
import com.won.StoreManageMent.auth.entity.AccountEntity;
import com.won.StoreManageMent.auth.entity.PlatFormEntity;
import com.won.StoreManageMent.auth.entity.StoreAuthInfoEntity;
import com.won.StoreManageMent.auth.repository.AccountRepository;
import com.won.StoreManageMent.auth.repository.PlatFormRepository;
import com.won.StoreManageMent.auth.repository.StoreAuthInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NaverAuthServiceImpl implements NaverAuthService {

    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StoreAuthInfoRepository storeAuthInfoRepository;

    @Autowired
    private PlatFormRepository platFormRepository;

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

        PlatFormEntity flatform = platFormRepository.findByName(insertFloatFormAuthKey.getFlatForm());

        if (account == null){
            return;
        }

        StoreAuthInfoEntity.StoreAuthInfoEntityBuilder platFormInfoBuilder = StoreAuthInfoEntity.builder()
                    .account(account)
                    .platformId(flatform)
                    .option1(passwordEncoder.encode(insertFloatFormAuthKey.getOption1()));

        if(insertFloatFormAuthKey.getOption2() != null && !insertFloatFormAuthKey.getOption2().isEmpty()){
            platFormInfoBuilder.option2(passwordEncoder.encode(insertFloatFormAuthKey.getOption2()));
        }

        StoreAuthInfoEntity platFormInfo = platFormInfoBuilder.build();

        storeAuthInfoRepository.save(platFormInfo);
    }
}
