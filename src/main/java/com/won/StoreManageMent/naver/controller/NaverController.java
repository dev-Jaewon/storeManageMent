package com.won.StoreManageMent.naver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.won.StoreManageMent.naver.dto.ResponseUploadImage;
import com.won.StoreManageMent.naver.service.NaverService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/naver")
public class NaverController {

    @Autowired
    private NaverService naverService;
    
    @PostMapping("/image")
    public ResponseUploadImage imageUpload(@RequestParam("files") ArrayList<MultipartFile> files) {
        return naverService.imageFreeHosting(files);
    }

}
