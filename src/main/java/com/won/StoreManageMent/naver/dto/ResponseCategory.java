package com.won.StoreManageMent.naver.dto;

import java.util.List;

import com.won.StoreManageMent.naver.dto.ResponseOriginProducts.ProductContentDTO.ChannelProductDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseCategory {
    private List<ProductContentDTO> contents;
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;

    @Getter
    @Setter
    public static class ProductContentDTO {
        private long originProductNo;
        private List<ChannelProductDTO> channelProducts;
        private String wholeCategoryName;
        private String categoryId;
        private String manufacturerCode;
        private String manufacturerName;
        private long brandCode;
        private String brandName;
        private long id;
        private String name;
       
    }
}
