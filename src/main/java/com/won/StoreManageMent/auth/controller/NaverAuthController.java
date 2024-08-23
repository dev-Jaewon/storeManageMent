package com.won.StoreManageMent.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.Playload;
import com.won.StoreManageMent.auth.dto.NaverAuthDto;
import com.won.StoreManageMent.auth.service.JwtService;
import com.won.StoreManageMent.auth.service.NaverAuthService;


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
    
}
