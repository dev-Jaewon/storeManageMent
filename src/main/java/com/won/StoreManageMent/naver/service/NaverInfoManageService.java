package com.won.StoreManageMent.naver.service;

import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo;
import com.won.StoreManageMent.naver.dto.ResponseCurrencyExchange;

public interface NaverInfoManageService {
    public String addNaverSellerInfo(RequestNaverSellerInfo requestNaverSellerInfo);
    public ResponseCurrencyExchange getCurrencyExchange(String country);
}
