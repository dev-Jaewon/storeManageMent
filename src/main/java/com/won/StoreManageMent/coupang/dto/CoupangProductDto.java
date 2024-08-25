package com.won.StoreManageMent.coupang.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class CoupangProductDto {
    
    @Getter
    @Setter
    public static class UploadInfo {
        // 카테고리 코드
        private String brand;
        private int salePrice;
        private int originalPrice;
        private int displayCategoryCode;
        private String displayProductName;
        // 발주서에 사용되는 상품명
        private String sellerProductName;
        private LocalDateTime saleEndedAt;
        private String deliveryMethod;
        private String deliveryCompanyCode;
        private String deliveryChargeType;
        private int deliveryCharge;
        private int freeShipOverAmount;
        private int deliveryChargeOnReturn;
        private String remoteAreaDeliverable;
        private String unionDeliveryType;
        private String returnChargeName;
        private String companyContactNumber;
        private String returnZipCode;
        private String returnAddress;
        private String returnAddressDetail;
        private String returnCenterCode;
        private String outboundShippingPlaceCode;
        private int returnCharge;
        private int vendorUserId;
        private boolean requested;
        private ArrayList<Items> items;

        @Getter
        @Setter
        public static class Items {
            private String itemName;
            private String originalPrice;
            private int salePrice;
            private String maximumBuyCount;
            private String maximumBuyForPerson;
            private String outboundShippingTimeDay;
            private String maximumBuyForPersonPeriod;
            private int unitCount;
            private String adultOnly;
            private String taxType;
            private String parallelImported = "NOT_PARALLEL_IMPORTED";
            private String overseasPurchased;
            private String pccNeeded;
            private ArrayList<String> searchTags;
            private ArrayList<String> images;
            private ArrayList<String> notices;
            private ArrayList<Contents> contents;
            private String offerCondition;
            private ArrayList<Attributes> attributes;

            @Getter
            @Setter
            public static class Contents {
                private String contentsType;
                private ArrayList<String> contentDetails;
            }

            @Getter
            @Setter
            public static class Attributes {
                private String attributeTypeName;
                private String attributeValueName;
            }
        }
    }

    @Getter
    @Setter
    private static class ResponseUpload {
        private String code;
        private String message;
        private ResponseData data;

        @Getter
        @Setter
        private static class ResponseData  {
            private String code;
            private String message;
            private long data;
        }
    }
}
