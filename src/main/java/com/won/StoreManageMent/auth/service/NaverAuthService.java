package com.won.StoreManageMent.auth.service;

import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.ResponseAuthToken;
import com.won.StoreManageMent.auth.dto.PlatFormInfoDto;
import com.won.StoreManageMent.auth.dto.ResponseAccount;

public interface NaverAuthService {
    public ResponseAuthToken naverLogin(String token);
    public void insertAuthKeyInfo(PlatFormInfoDto.InsertFlatFormAuthInfo insertFloatFormAuthKey);
    public void updateAuthKeyInfo(PlatFormInfoDto.UpdateFlatFormAuthInfo insertFloatFormAuthKey);
    public ResponseAccount getAccountInfo();
    public ResponseAuthToken getNewAccessToken();
}
