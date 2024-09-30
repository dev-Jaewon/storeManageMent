package com.won.StoreManageMent.naver.vo;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeCurrencyVo {
    private int code;
    private String message;
    private List<Data> data;
    private int totalPages;
    private int totalCount;
    private int currentPage;
    private int pageSize;

    @Getter
    @Setter
    public static class Data {
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
        private boolean tcBuyingPrice;
        private boolean fcSellingPrice;
        private double exchangeCommission;
        private double usDollarRate;
        private ChartImageUrl chartImageUrl;

        @Getter
        @Setter
        public static class ChartImageUrl {
            private String day;
            private String month;
            private String month3;
            private String year;
        }
    }
}
