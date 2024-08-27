package com.won.StoreManageMent.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.Playload;
import com.won.StoreManageMent.auth.dto.NaverAuthDto;
import com.won.StoreManageMent.auth.dto.PlatFormInfoDto;
import com.won.StoreManageMent.auth.service.JwtService;
import com.won.StoreManageMent.auth.service.NaverAuthService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/auth")
public class NaverAuthController {

    @Autowired
    private NaverAuthService naverAuthService;

    @Autowired
    private JwtService jwtService;
    
    @GetMapping("/naver")
    public String requestMethodName(@RequestParam("token") String token) {

        NaverAuthDto resNaverInfo = naverAuthService.naverLogin(token);

        NaverAuthDto.NaverUserInfoResponse userInfo = resNaverInfo.getResponse();

        if(userInfo == null){
            return null;
        }

        Playload jwtPlayLoad = new Playload(userInfo.getId(),userInfo.getNickname(),userInfo.getProfileImage());

        return jwtService.createToken(jwtPlayLoad);
    }

    @GetMapping("/check")
    public String checkAuthToken(@RequestParam("token") String token){

        return jwtService.checkToken(token);
    }

    @PostMapping("/flatForm")
    public void insertFloatFormAuthKeyInfo(@RequestBody PlatFormInfoDto.InsertFlatFormAuthInfo insertFlatFormAuthInfo){
        naverAuthService.insertAuthKeyInfo(insertFlatFormAuthInfo);
    }

    @PutMapping("/flatForm")
    public void updateFloatFormAuthKeyInfo(@RequestBody PlatFormInfoDto.UpdateFlatFormAuthInfo updateFlatFormAuthInfo){
        naverAuthService.updateAuthKeyInfo(updateFlatFormAuthInfo);
    }
}
