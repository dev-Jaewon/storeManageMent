package com.won.StoreManageMent.naver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class ResponseProductList {
    private List<Product> list;
    private int totalCount;
    private int lastPage;

    @Getter
    @Builder
    public static class Product {
        private int price;
        private long id;
        private String title;
        private int margin;
        private int profit;
        private String image;
        private String linkStore;
        private String linkProduct;
        private LocalDateTime createat;
    }
}
