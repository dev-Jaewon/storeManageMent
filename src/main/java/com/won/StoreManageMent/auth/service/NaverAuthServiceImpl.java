package com.won.StoreManageMent.auth.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.won.StoreManageMent.auth.dto.JwtPayLoadDto;
import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.ResponseAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseAuthToken naverLogin(String token){
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

            NaverAuthDto.NaverUserInfoResponse naverAuthInfo = naverAuthDto.getResponse();

            AccountEntity account = accountRepository.findByPlatformId(naverAuthInfo.getId());

//            if(userInfo == null){
//                return null;
//            }
//
            JwtPayLoadDto.Playload jwtPlayLoad = JwtPayLoadDto.Playload.builder()
                    .id(account.getId())
                    .nickName(account.getNickName())
                    .platformId(account.getPlatformId())
                    .build();

            return jwtService.createToken(jwtPlayLoad);

        }catch(URISyntaxException | IOException | InterruptedException e){
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        }
    }

    @Override
    public void insertAuthKeyInfo(PlatFormInfoDto.InsertFlatFormAuthInfo insertFlatFormAuthInfo){

        AccountEntity account = accountRepository.findById(insertFlatFormAuthInfo.getAccountId())
                .orElseThrow(null);

        if (account == null){
            return;
        }

        PlatFormEntity flatform = platFormRepository.findByName(insertFlatFormAuthInfo.getFlatForm());

                
        StoreAuthInfoEntity.StoreAuthInfoEntityBuilder platFormInfoBuilder = StoreAuthInfoEntity.builder()
                    .account(account)
                    .platformId(flatform)
                    .option1(passwordEncoder.encode(insertFlatFormAuthInfo.getOption1()));

        if(insertFlatFormAuthInfo.getOption2() != null && !insertFlatFormAuthInfo.getOption2().isEmpty()){
            platFormInfoBuilder.option2(passwordEncoder.encode(insertFlatFormAuthInfo.getOption2()));
        }

        StoreAuthInfoEntity platFormInfo = platFormInfoBuilder.build();


        storeAuthInfoRepository.save(platFormInfo);
    }

    @Override
    public void updateAuthKeyInfo(PlatFormInfoDto.UpdateFlatFormAuthInfo updateFlatFormAuthInfo){

        if (updateFlatFormAuthInfo.getId() == null){
            return;
        }

        StoreAuthInfoEntity platFormInfo = storeAuthInfoRepository.findById(updateFlatFormAuthInfo.getId())
                .orElseThrow(null);

        platFormInfo.setOption1(passwordEncoder.encode(updateFlatFormAuthInfo.getOption1()));

        if(updateFlatFormAuthInfo.getOption2() == null){
            platFormInfo.setOption2(null);
        }else{
            platFormInfo.setOption2(passwordEncoder.encode(updateFlatFormAuthInfo.getOption2()));
        }

        storeAuthInfoRepository.save(platFormInfo);

    }
}
