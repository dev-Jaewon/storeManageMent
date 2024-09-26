package com.won.StoreManageMent.auth.dto;

import lombok.*;

@Getter
@Setter
public class JwtPayLoadDto {
    
    @Getter
    @Setter
    @Builder
    public static class Playload {
        private Long id;
        private String nickName;
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
