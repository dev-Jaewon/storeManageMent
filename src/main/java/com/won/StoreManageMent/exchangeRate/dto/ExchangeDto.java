package com.won.StoreManageMent.exchangeRate.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ExchangeDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseCurrency {
        private double price;
    }

    @Getter
    @Setter
    public static class RequestExChangeRate {
        private String currency;
        private int page = 1;
        private int perPage = 10;
    }

    @Getter
    public static class DaumFinanceCurrencyInfo {
        private int code;
        private String message;
        private ArrayList<CurrencyData> data;
        private int totalPages;
        private int totalCount;
        private int currentPage;
        private int pageSize;

        @Getter
        public static class CurrencyData {
            private String symbolCode;
            private String date;
            private int recurrenceCount;
            private double basePrice;
            private String change;
            private double changePrice;
            private double changeRate;
            private double cashBuyingPrice;
            private double cashSellingPrice;
            private double ttBuyingPrice;
            private double ttSellingPrice;
            private Double tcBuyingPrice;
            private Double fcSellingPrice;
            private double exchangeCommission;
            private double usDollarRate;
            private ChartImageUrl chartImageUrl;

            @Getter
            public static class ChartImageUrl {
                private String day;
                private String month;
                private String month3;
                private String year;
            }
        }
    }
    
}
