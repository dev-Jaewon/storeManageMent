package com.won.StoreManageMent.exchangeRate.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class ExchangeDto {
    
    @Getter
    @Setter
    public static class RequestExchangeRate {
        private ArrayList<String> countries;
    }

    @Getter
    @Setter
    public static class ResponseCurrency {
        private ArrayList<Currency> currencies;

        @Getter
        @Setter
        public static class Currency {
            // 결과
            // 1 : 성공, 2 : DATA코드 오류, 3 : 인증코드 오류, 4 : 일일제한횟수 마감
            private int result;

            // 통화코드
            @JsonProperty("cur_unit")
            private String curUnit;

            // 전신환(송금) 받을때
            private String ttb;

            // 전신환(송금) 보낼때
            private String tts;

            // 매매 기준율
            @JsonProperty("deal_bas_r")
            private String dealBasR;

            // 장부가격
            private String bkpr;

            // 년환가료율
            @JsonProperty("yy_efee_r")
            private String yyEfeeR;

            // 10일환가료율
            @JsonProperty("ten_dd_efee_r")
            private String tenDdEfeeR;

            // 서울외국환중개 매매기준율
            @JsonProperty("kftc_bkpr")
            private String kftcBkpr;

            // 서울외국환중개 장부가격
            @JsonProperty("kftc_deal_bas_r")
            private String kftcDealBasR;

            // 국가/통화명
            @JsonProperty("cur_nm")
            private String curNm;
        }
    }


}
