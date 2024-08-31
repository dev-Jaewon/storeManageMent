package com.won.StoreManageMent.exchangeRate.service;

import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.RequestExChangeRate;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.ResponseCurrency;

public interface ExchangeRateService {
    public ResponseCurrency getCurrency(RequestExChangeRate currencyInfo);
}
