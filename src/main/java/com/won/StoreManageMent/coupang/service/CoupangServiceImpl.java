package com.won.StoreManageMent.coupang.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coupang.openapi.sdk.Hmac;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.coupang.dto.CoupangDto;
import com.won.StoreManageMent.coupang.dto.CoupangDto.ResponseOrderInfo;
import com.won.StoreManageMent.coupang.dto.CoupangDto.ResponseReturnInfo;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CoupangServiceImpl implements CoupangService {

    private final HttpClient httpClient;
    
    private final String URL = "https://api-gateway.coupang.com";
    
    @Value("${coupang.vender_id}")
    private String VENDER_ID;

    @Value("${coupang.secret_key}")
    private String SECRET_KEY;

    @Value("${coupang.access_key}")
    private String ACCESS_KEY;

    @Override
    public List<ResponseOrderInfo> getOrders(){
        List<String> ENG_STATUS = Arrays.asList("ACCEPT", "INSTRUCT", "DEPARTURE", "DELIVERING", "FINAL_DELIVERY", "NONE_TRACKING");
        List<String> KOR_STATUS = Arrays.asList("결제완료", "상품준비중", "배송지시", "배송중", "배송완료", "직접배송");

        List<ArrayList<CoupangDto.OrderData>> statusList = requestCouPangInfo(
                ENG_STATUS,
                this::requestOrder
        );

        return IntStream.range(0, statusList.size())
                    .mapToObj(i -> new ResponseOrderInfo(KOR_STATUS.get(i), statusList.get(i)))
                    .collect(Collectors.toList());
    }

    @Override
    public List<ResponseReturnInfo> getReturns(){
        List<String> ENG_STATUS = Arrays.asList("CANCEL", "RETURN");
        List<String> KOR_STATUS = Arrays.asList("취소", "반품");

        List<ArrayList<CoupangDto.ReturnData>> statusList = requestCouPangInfo(
                ENG_STATUS,
                this::requestReturn
        );

        return IntStream.range(0, KOR_STATUS.size())
                        .mapToObj(i -> new CoupangDto.ResponseReturnInfo(KOR_STATUS.get(i), statusList.get(i)))
                        .collect(Collectors.toList());
    }

    @Override
    public List<CoupangDto.ResponseExchangeInfo> getExchange(){
        List<String> ENG_STATUS = Arrays.asList("RECEIPT");
        List<String> KOR_STATUS = Arrays.asList("교환");

        List<ArrayList<CoupangDto.ExchangeData>> statusList = requestCouPangInfo(
                ENG_STATUS,
                this::requestExchange
        );

        return IntStream.range(0, KOR_STATUS.size())
                        .mapToObj(i -> new CoupangDto.ResponseExchangeInfo(KOR_STATUS.get(i), statusList.get(i)))
                        .collect(Collectors.toList());
    }

    @Override
    public List<CoupangDto.ResponseOnlineInquiriesInfo> getOnlineInquiries(){
        List<String> ENG_STATUS = Arrays.asList("ALL");
        List<String> KOR_STATUS = Arrays.asList("모두");

        List<ArrayList<CoupangDto.OnlineInquiriesData>> statusList = requestCouPangInfo(
                ENG_STATUS,
                this::requestoOlineInquiries
        );

        return IntStream.range(0, KOR_STATUS.size())
                        .mapToObj(i -> new CoupangDto.ResponseOnlineInquiriesInfo(KOR_STATUS.get(i), statusList.get(i)))
                        .collect(Collectors.toList());
    }


    private <T> List<ArrayList<T>> requestCouPangInfo(
            List<String> engStatus,
            Function<String, CompletableFuture<ArrayList<T>>> requestFunction){

            List<CompletableFuture<ArrayList<T>>> requestFutures = engStatus.stream()
                    .map(requestFunction)
                    .collect(Collectors.toList());

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(requestFutures.stream()
                    .toArray(CompletableFuture<?>[]::new));

            return allFutures.thenApply(v -> requestFutures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList()))
                    .join();
    }

    private <T> CompletableFuture<ArrayList<T>> requestApi(
        String method,
        String path,
        Function<HttpResponse<String>,
        ArrayList<T>> parseData){

        String authorization = getAuth(method, path);

        try {

            HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(URL + path))
                        .header("Authorization", authorization)
                        .header("content-type", "application/json;charset=UTF-8")
                        .GET()
                        .build();

            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(parseData)
                        .exceptionally(e -> new ArrayList<>());
            
        } catch (RuntimeException | URISyntaxException e) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }
    }


    private CompletableFuture<ArrayList<CoupangDto.OrderData>> requestOrder(String status){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeDate = now.minus(31, ChronoUnit.DAYS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String method = "GET";
        String path = "/v2/providers/openapi/apis/api/v4/vendors/" + VENDER_ID +
                      "/ordersheets?createdAtFrom=" + beforeDate.format(formatter) +
                      "&createdAtTo=" + now.format(formatter) +
                      "&status=" + status;

        return requestApi(
            method,
            path, 
            response -> {
                try {
                    return new ObjectMapper().readValue(response.body(), CoupangDto.OrderInfo.class).getData();
                } catch (JsonProcessingException e) {
                    return new ArrayList<CoupangDto.OrderData>();
                }
            });
    }

    private CompletableFuture<ArrayList<CoupangDto.ReturnData>> requestReturn(String status){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeDate = now.minus(31, ChronoUnit.DAYS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        String method = "GET";
        String path = "/v2/providers/openapi/apis/api/v4/vendors/" + VENDER_ID +
                      "/returnRequests?searchType=timeFrame&createdAtFrom=" + beforeDate.format(formatter) +
                      "&createdAtTo=" + now.format(formatter) +
                      "&status=UC&cancelType" + status;

        
        return requestApi(
            method,
            path, 
            response -> {
                try {
                    return new ObjectMapper().readValue(response.body(), CoupangDto.ReturnInfo.class).getData();
                } catch (JsonProcessingException e) {
                    return new ArrayList<CoupangDto.ReturnData>();
                }
            });
    }

    private CompletableFuture<ArrayList<CoupangDto.ExchangeData>> requestExchange(String status){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeDate = now.minus(7, ChronoUnit.DAYS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String method = "GET";
        String path = "/v2/providers/openapi/apis/api/v4/vendors/" + VENDER_ID +
                      "/exchangeRequests?createdAtFrom=" + beforeDate.format(formatter) +
                      "&createdAtTo=" + now.format(formatter) +
                      "&status=" + status;

        return requestApi(
            method,
            path, 
            response -> {
                try {
                    return new ObjectMapper().readValue(response.body(), CoupangDto.ExchangeInfo.class).getData();
                } catch (JsonProcessingException e) {
                    return new ArrayList<CoupangDto.ExchangeData>();
                }
            });
    }

    private CompletableFuture<ArrayList<CoupangDto.OnlineInquiriesData>> requestoOlineInquiries(String status){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeDate = now.minus(6, ChronoUnit.DAYS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String method = "GET";
        String path = "/v2/providers/openapi/apis/api/v4/vendors/" + VENDER_ID +
                      "/onlineInquiries?inquiryStartAt=" + beforeDate.format(formatter) +
                      "&inquiryEndAt=" + now.format(formatter) +
                      "&vendorId=" + VENDER_ID +
                      "&answeredType=" + status;

        return requestApi(
            method,
            path, 
            response -> {
                try {
                    return new ObjectMapper().readValue(response.body(), CoupangDto.OnlineInquiriesInfo.class).getData();
                } catch (JsonProcessingException e) {
                    return new ArrayList<CoupangDto.OnlineInquiriesData>();
                }
            });
    }

    private String getAuth(String method, String url){
        return Hmac.generate(method, url, SECRET_KEY, ACCESS_KEY);
    }

}