package com.won.StoreManageMent.naver.service;

import java.util.ArrayList;

import com.won.StoreManageMent.naver.dto.*;
import org.springframework.web.multipart.MultipartFile;

public interface NaverApiService {
    public ResponseAuthToken newAuthToken();
    public ResponseUploadImage imageFreeHosting(ArrayList<MultipartFile> files);
    public ResponseOriginProducts originProducts(NaverProductsDto naverProductsDto);
    public ResponseCategory getCategoryInfo(String keyword);
}
