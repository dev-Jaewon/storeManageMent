package com.won.StoreManageMent.naver.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.won.StoreManageMent.naver.dto.NaverProductsDto;
import com.won.StoreManageMent.naver.dto.ResponseAuthToken;
import com.won.StoreManageMent.naver.dto.ResponseCategory;
import com.won.StoreManageMent.naver.dto.ResponseOriginProducts;
import com.won.StoreManageMent.naver.dto.ResponseUploadImage;

public interface NaverService {
    public ResponseAuthToken newAuthToken();
    public ResponseUploadImage imageFreeHosting(ArrayList<MultipartFile> files);
    public ResponseOriginProducts originProducts(NaverProductsDto naverProductsDto);
    public ResponseCategory getCategoryInfo(String keyword);
}
