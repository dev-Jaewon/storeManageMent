package com.won.StoreManageMent.coupang.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.won.StoreManageMent.coupang.dto.CoupangDto;

public interface CoupangService {
    public CompletableFuture<ArrayList<CoupangDto.OrderData>> getOrders(String status);
}
