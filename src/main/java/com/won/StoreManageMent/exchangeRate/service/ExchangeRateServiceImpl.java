package com.won.StoreManageMent.exchangeRate.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.exchangeRate.dto.CalculateDto.CalculateExchange;
import com.won.StoreManageMent.exchangeRate.dto.CalculateDto.RequestCalculateCurrency;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.DaumFinanceCurrencyInfo;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.RequestExChangeRate;
import com.won.StoreManageMent.exchangeRate.dto.ExchangeDto.ResponseCurrency;
import com.won.StoreManageMent.exchangeRate.entity.DeliveryPriceEntity;
import com.won.StoreManageMent.exchangeRate.repository.DeliveryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService{

    private final HttpClient httpClient;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
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
           
            double currency = naverAuthDto.getData().get(0).getTtSellingPrice();

            if (currencyInfo.getCurrency() == "JPY"){
                return new ResponseCurrency(Math.round((currency / 100) * 100) / 100.0);
            }
            
            return new ResponseCurrency(currency);

        }catch(URISyntaxException | IOException | InterruptedException e){
            System.out.println(e);
            return new ResponseCurrency();
        }
    }

    @Override
    public CalculateExchange calculateCurrency(RequestCalculateCurrency calculateCurrency){

        if (calculateCurrency.getWeight() > 0) {
  
            String findCountry = "";

            switch(calculateCurrency.getCurrency()){
                case "JPY":
                    findCountry = "japan";
                    break;
                case "EUR":
                    findCountry = "germany";
                    break;
                case "USD":
                    findCountry = "us";
                    break;
                case "GBP":
                    findCountry = "uk";
                    break;
                default:
                    findCountry = "";
                    break;
            }

            if(findCountry == ""){
                return new CalculateExchange();
            }

            
            double searchWeight = 0;

            if(Math.round(calculateCurrency.getWeight()) - calculateCurrency.getWeight() >= 0){
                searchWeight = Math.round(calculateCurrency.getWeight());
            }else{
                searchWeight = Math.round(calculateCurrency.getWeight()) + 0.5;
            }

            
            DeliveryPriceEntity deliveryInfo = deliveryRepository.findByCountryAndKg(findCountry, String.valueOf(searchWeight));

            double weightPrice = 0;

            switch (calculateCurrency.getCurrency()) {
                case "JPY":
                    weightPrice = Double.parseDouble(deliveryInfo.getPrice()) * calculateCurrency.getCurrencyPrice();
                    break;
                case "EUR":
                    weightPrice = Integer.parseInt(deliveryInfo.getPrice());
                    break;
                case "USD":
                    weightPrice = Double.parseDouble(deliveryInfo.getPrice()) * calculateCurrency.getCurrencyPrice();
                    break;
                case "GBP":
                    weightPrice = Double.parseDouble(deliveryInfo.getPrice());
                    break;
            }




            double cardCommission = calculateCurrency.getSellPrice() * 1.01;
            double globalCommission = cardCommission * 0.0018 * calculateCurrency.getCurrencyPrice();
                
            double originProductPrice = cardCommission * calculateCurrency.getCurrencyPrice() + globalCommission;
            double sumProductAndWeightPrice = originProductPrice + weightPrice;
            
            double temp = sumProductAndWeightPrice / (1 - ((calculateCurrency.getMargin() / 100) + 0.068));

            double importPrice = sumProductAndWeightPrice;
            double profit = Math.ceil((temp * 0.9426 - sumProductAndWeightPrice) / 100) * 100;
            double sellPrice = Math.ceil(temp / 100) * 100;

            return new CalculateExchange(importPrice, profit, sellPrice);

        }
        
        return new CalculateExchange();
    }
}
