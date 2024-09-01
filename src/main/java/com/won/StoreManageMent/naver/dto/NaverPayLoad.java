package com.won.StoreManageMent.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NaverPayLoad{
    private long timestamp;
    private String client_id;
    private String grant_type;
    private String client_secret_sign;
    private String type;
}