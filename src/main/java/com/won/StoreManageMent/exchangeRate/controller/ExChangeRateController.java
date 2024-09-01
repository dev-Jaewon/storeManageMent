package com.won.StoreManageMent.exchangeRate.controller;

import org.springframework.web.bind.annotation.RestController;

import com.won.StoreManageMent.exchangeRate.dto.CalculateDto.CalculateExchange;
import com.won.StoreManageMent.exchangeRate.dto.CalculateDto.RequestCalculateCurrency;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.RequestExChangeRate;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.ResponseCurrency;
import com.won.StoreManageMent.exchangeRate.service.ExchangeRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/exchange")
public class ExChangeRateController {
    
    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/currency")
    public ResponseCurrency exChangeRate(@ModelAttribute RequestExChangeRate requestExChangeRate) 
    {
        return exchangeRateService.getCurrency(requestExChangeRate);
    }

    @PostMapping("/calculate/sellprice")
    public CalculateExchange calculateSellPrice(@RequestBody RequestCalculateCurrency calculateCurrency){
        return exchangeRateService.calculateCurrency(calculateCurrency);
    }
}
