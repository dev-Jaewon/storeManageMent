package com.won.StoreManageMent.naver.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.ResponseAuthToken;

public interface NaverService {
    public ResponseAuthToken newAuthToken();
}
