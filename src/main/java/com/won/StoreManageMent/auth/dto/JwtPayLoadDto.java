package com.won.StoreManageMent.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class JwtPayLoadDto {
    
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Playload {
        private String id;
        private String platform;
        private String platformId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Token {
        private Boolean state;
        private String value;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseAuthToken {
        private String accessToken;
        private String refreshTOken;
    }
}
