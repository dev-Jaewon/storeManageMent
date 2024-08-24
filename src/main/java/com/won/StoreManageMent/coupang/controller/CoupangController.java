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
import com.won.StoreManageMent.coupang.dto.CoupangDto.ResponseOrderInfo;
import com.won.StoreManageMent.coupang.service.CoupangService;



@RestController
@RequestMapping("/coupang")
public class CoupangController {

    @Autowired
    private CoupangService coupangService;
    
    @GetMapping("/orderInfo")
    public List<ResponseOrderInfo> orderInfo() {
        
        List<String> ENG_STATUS = Arrays.asList("ACCEPT", "INSTRUCT", "DEPARTURE", "DELIVERING", "FINAL_DELIVERY", "NONE_TRACKING");
        List<String> KOR_STATUS = Arrays.asList("결제완료", "상품준비중", "배송지시", "배송중", "배송완료", "직접배송");

        List<CompletableFuture<ArrayList<CoupangDto.OrderData>>> orderRequest = ENG_STATUS.stream()
                    .map(coupangService::getOrders)
                    .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(orderRequest.stream()
                    .toArray(CompletableFuture<?>[]::new));

        List<ArrayList<CoupangDto.OrderData>> statusList = allFutures.thenApply(v -> orderRequest.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList()))
                    .join();

        return IntStream.range(0, statusList.size())
                    .mapToObj(i -> new ResponseOrderInfo(KOR_STATUS.get(i), statusList.get(i)))
                    .collect(Collectors.toList());

    }
    
}
