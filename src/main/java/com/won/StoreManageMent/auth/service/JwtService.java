package com.won.StoreManageMent.auth.service;

import com.won.StoreManageMent.auth.dto.JwtPayLoadDto.Playload;

public interface JwtService {
    public String createToken(Playload playload);
}
