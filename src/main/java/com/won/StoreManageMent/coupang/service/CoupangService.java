package com.won.StoreManageMent.coupang.service;

import java.util.List;

import com.won.StoreManageMent.coupang.dto.CoupangDto;

public interface CoupangService {
    public List<CoupangDto.ResponseOrderInfo> getOrders();
    public List<CoupangDto.ResponseReturnInfo> getReturns();
    public List<CoupangDto.ResponseExchangeInfo> getExchange();
}
