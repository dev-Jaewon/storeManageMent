package com.won.StoreManageMent.naver.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseOriginProducts {

    private List<ProductContentDTO> contents;
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
    private SortDTO sort;
    private boolean first;
    private boolean last;


    @Setter
    @Getter
    public static class SortDTO {

        private boolean sorted;
        private List<SortFieldDTO> fields;


        @Setter
        @Getter
        public static class SortFieldDTO {
            private String name;
            private String direction;
        
        }
    }

    @Setter
    @Getter
    public static class ProductContentDTO {

        private long originProductNo;
        private List<ChannelProductDTO> channelProducts;

        @Setter
        @Getter
        public static class ChannelProductDTO {

            private long originProductNo;
            private long channelProductNo;
            private String channelServiceType;
            private String categoryId;
            private String name;
            private String statusType;
            private String channelProductDisplayStatusType;
            private int salePrice;
            private int discountedPrice;
            private int mobileDiscountedPrice;
            private int stockQuantity;
            private boolean knowledgeShoppingProductRegistration;
            private String deliveryAttributeType;
            private int deliveryFee;
            private int returnFee;
            private int exchangeFee;
            private int managerPurchasePoint;
            private String wholeCategoryName;
            private String wholeCategoryId;
            private ImageDTO representativeImage;
            private String brandName;
            private String manufacturerName;
            private List<SellerTagDTO> sellerTags;
            private String regDate;
            private String modifiedDate;


            @Setter
            @Getter
            public static class ImageDTO {
                private String url;
            }


            @Setter
            @Getter
            public static class SellerTagDTO {
                private int code;
                private String text;
            }
        }

    }
    
}


