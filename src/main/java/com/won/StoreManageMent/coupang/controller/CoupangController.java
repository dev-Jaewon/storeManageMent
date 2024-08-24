package com.won.StoreManageMent.coupang.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.won.StoreManageMent.coupang.dto.CoupangDto;
import com.won.StoreManageMent.coupang.service.CoupangService;



@RestController
@RequestMapping("/coupang")
public class CoupangController {

    @Autowired
    private CoupangService coupangService;
    
    @GetMapping("/orderInfo")
    public CoupangDto.ResponseCoupangInfo orderInfo() {
        
       

    }
    
}
