package com.won.StoreManageMent.naver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestProductList {
    private int page;
    private int size;
    private String titleKeyword;
}
