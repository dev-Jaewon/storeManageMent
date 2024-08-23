package com.won.StoreManageMent.auth.service;

import com.won.StoreManageMent.auth.dto.NaverAuthDto;

public interface NaverAuthService {
    public NaverAuthDto naverLogin(String token);
}
