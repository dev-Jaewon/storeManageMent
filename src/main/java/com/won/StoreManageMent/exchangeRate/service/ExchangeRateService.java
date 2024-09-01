package com.won.StoreManageMent.exchangeRate.service;

import com.won.StoreManageMent.exchangeRate.dto.CalculateDto.CalculateExchange;
import com.won.StoreManageMent.exchangeRate.dto.CalculateDto.RequestCalculateCurrency;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.RequestExChangeRate;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.ResponseCurrency;

public interface ExchangeRateService {
    public ResponseCurrency getCurrency(RequestExChangeRate currencyInfo);
    public CalculateExchange calculateCurrency(RequestCalculateCurrency calculateCurrency);
}
