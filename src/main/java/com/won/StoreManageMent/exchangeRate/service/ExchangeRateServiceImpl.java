package com.won.StoreManageMent.exchangeRate.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.DaumFinanceCurrencyInfo;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.RequestExChangeRate;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.ResponseCurrency;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService{

    private final HttpClient httpClient;

    public ResponseCurrency getCurrency(RequestExChangeRate currencyInfo){

        try{
        
            String NAVER_AUTH_API = "https://finance.daum.net/api/exchanges/FRX.KRW" + currencyInfo.getCurrency() +
                                    "/days?symbolCode=FRX.KRW" +  currencyInfo.getCurrency() +
                                    "&terms=days&page=" + currencyInfo.getPage() + 
                                    "&perPage=" + currencyInfo.getPerPage();

            HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(NAVER_AUTH_API))
                        .header("referer", "https://finance.daum.net/exchanges/FRX.KRW" + currencyInfo.getCurrency())
                        .header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36")
                        .GET()
                        .build();

            String res = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();

            ObjectMapper objectMapper = new ObjectMapper();
            DaumFinanceCurrencyInfo naverAuthDto = objectMapper.readValue(res, DaumFinanceCurrencyInfo.class);
            
            return new ResponseCurrency(naverAuthDto.getData().get(0).getTtSellingPrice());

        }catch(URISyntaxException | IOException | InterruptedException e){
            System.out.println(e);
            return new ResponseCurrency();
        }
    }

}
