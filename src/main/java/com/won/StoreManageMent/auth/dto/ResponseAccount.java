package com.won.StoreManageMent.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseAccount {
    private long id;
    private String nickName;
    private String userImage;
}
