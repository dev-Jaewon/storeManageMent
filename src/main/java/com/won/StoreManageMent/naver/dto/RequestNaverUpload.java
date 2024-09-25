package com.won.StoreManageMent.naver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestNaverUpload {
    private ProductInfo info;
    private String original;

    @Getter
    @Setter
    public static class ProductInfo {
        private String thumbnail;
        private String originUrl;
        private String brand;
        private String title;
        private String price;
        private String category;
        private String detail;
        private String incomPrice;
        private String sale;
        private String tag;
    }
}
