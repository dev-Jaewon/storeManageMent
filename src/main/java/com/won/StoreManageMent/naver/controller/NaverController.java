package com.won.StoreManageMent.naver.controller;

import com.won.StoreManageMent.naver.dto.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.won.StoreManageMent.aop.Auth;
import com.won.StoreManageMent.naver.service.NaverApiService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/naver")
public class NaverController {

    @Autowired
    private NaverApiService naverApiService;
    
    @Auth
    @PostMapping("/image")
    public ResponseUploadImage imageUpload(@RequestParam("files") ArrayList<MultipartFile> files) {
        return naverApiService.imageFreeHosting(files);
    }

    @Auth
    @GetMapping("/origin/products")
    public ResponseOriginProducts naverProducts(@ModelAttribute NaverProductsDto naverProductsDto) {
        return naverApiService.originProducts(naverProductsDto);
    }

    @Auth
    @GetMapping("/category")
    public ResponseCategory naverCategoryInfo(@RequestParam("keyword") String keyword){
        return naverApiService.getCategoryInfo(keyword);
    }
}
