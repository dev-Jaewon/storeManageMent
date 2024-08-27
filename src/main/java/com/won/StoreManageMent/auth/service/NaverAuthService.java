package com.won.StoreManageMent.auth.service;

import com.won.StoreManageMent.auth.dto.NaverAuthDto;
import com.won.StoreManageMent.auth.dto.PlatFormInfoDto;

public interface NaverAuthService {
    public NaverAuthDto naverLogin(String token);
    public void insertAuthKeyInfo(PlatFormInfoDto.InsertFlatFormAuthInfo insertFloatFormAuthKey);
    public void updateAuthKeyInfo(PlatFormInfoDto.UpdateFlatFormAuthInfo insertFloatFormAuthKey);
}
