package com.won.StoreManageMent.coupang.service;

import java.util.List;

import com.won.StoreManageMent.coupang.dto.CoupangDto.ResponseOrderInfo;

public interface CoupangService {
    public List<ResponseOrderInfo> getOrders(String status);
}
