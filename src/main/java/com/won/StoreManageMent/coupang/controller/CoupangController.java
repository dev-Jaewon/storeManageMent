package com.won.StoreManageMent.coupang.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.won.StoreManageMent.coupang.dto.CoupangCategoryDto;
import com.won.StoreManageMent.coupang.dto.CoupangDto.ResponseCoupangInfo;
import com.won.StoreManageMent.coupang.dto.CoupangDto.ResponseExchangeInfo;
import com.won.StoreManageMent.coupang.dto.CoupangDto.ResponseOnlineInquiriesInfo;
import com.won.StoreManageMent.coupang.dto.CoupangDto.ResponseOrderInfo;
import com.won.StoreManageMent.coupang.dto.CoupangDto.ResponseReturnInfo;
import com.won.StoreManageMent.coupang.service.CoupangMetaDataService;
import com.won.StoreManageMent.coupang.service.CoupangService;



@RestController
@RequestMapping("/coupang")
public class CoupangController {

    @Autowired
    private CoupangService coupangService;

    @Autowired
    private CoupangMetaDataService coupangMetaDataService;
    
    @GetMapping("/info")
    public ResponseCoupangInfo orderInfo() {

        CompletableFuture<List<ResponseOrderInfo>> orderInfoFuture =  CompletableFuture.supplyAsync(coupangService::getOrders);
        CompletableFuture<List<ResponseReturnInfo>> returnInfoFuture =  CompletableFuture.supplyAsync(coupangService::getReturns);
        CompletableFuture<List<ResponseExchangeInfo>> exchangeInfoFuture =  CompletableFuture.supplyAsync(coupangService::getExchange);
        CompletableFuture<List<ResponseOnlineInquiriesInfo>> OnlineInquiriesInfoFuture =  CompletableFuture.supplyAsync(coupangService::getOnlineInquiries);

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(orderInfoFuture, returnInfoFuture, exchangeInfoFuture, OnlineInquiriesInfoFuture);

        return allFutures.thenApply(v -> {
            try {
                List<ResponseOrderInfo> OrderData = orderInfoFuture.get();
                List<ResponseReturnInfo> returnData= returnInfoFuture.get();
                List<ResponseExchangeInfo> ExchangeData = exchangeInfoFuture.get();
                List<ResponseOnlineInquiriesInfo> OnlineInquiriesData = OnlineInquiriesInfoFuture.get();

                
                return new ResponseCoupangInfo(OrderData, returnData,ExchangeData, OnlineInquiriesData);
            } catch (RuntimeException | ExecutionException | InterruptedException e) {
                return new ResponseCoupangInfo();
            }
        }).join();
        
    }

    @GetMapping("/category/{code}")
    public CoupangCategoryDto.CategoryData getCategoryMetaData(@PathVariable("code") String code) {
        return coupangMetaDataService.getCategoryData(code);
    }
}
