package com.won.StoreManageMent.naver.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseUploadImage {

    private List<Image> images;

    @Getter
    @Setter
    private static class Image {
        private String url;
    }
}
