package com.won.StoreManageMent.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverAuthDto {
    private String resultcode;
    private String message;
    private NaverUserInfoResponse response;

    @Getter
    @Setter
    public static class NaverUserInfoResponse {
        private String id;
        private String nickname;
        @JsonProperty("profile_image")
        private String profileImage;
    }
}
