package com.won.StoreManageMent.exchangeRate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CalculateDto {
    
    @Getter
    @Setter
    public static class RequestCalculateCurrency {
        private String currency;
        private double currencyPrice;
        private double sellPrice;
        private double margin;
        private double weight;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CalculateExchange {
        private double importPrice;
        private double profit;
        private double sellPrice;
    }
}
