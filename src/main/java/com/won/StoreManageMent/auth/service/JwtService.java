package com.won.StoreManageMent.auth.service;

import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.Playload;
import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.ResponseAuthToken;

public interface JwtService {
    public ResponseAuthToken createToken(Playload playload);
}
