package com.won.StoreManageMent.naver.controller;

import com.won.StoreManageMent.naver.dto.*;
import com.won.StoreManageMent.naver.service.NaverInfoManageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private NaverInfoManageService naverInfoManageService;

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

    @Auth
    @PostMapping("/sellerInfo")
    public ResponseEntity<String> insertNaverSellerInfo(@RequestParam(required = false) RequestNaverSellerInfo requestNaverSellerInfo){
        try{
            String message = naverInfoManageService.addNaverSellerInfo(requestNaverSellerInfo);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Auth
    @GetMapping("/product")
    public ResponseEntity<ResponseProductList> productList(@ModelAttribute RequestProductList requestProductList){
        ResponseProductList productList = naverApiService.productList(requestProductList);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @Auth
    @GetMapping("/tag")
    public ResponseEntity<ResponseTags> searchTag(@RequestParam("keyword") String keyword ){
        ResponseTags responseTags = naverApiService.searchTags(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(responseTags);
    }
}
