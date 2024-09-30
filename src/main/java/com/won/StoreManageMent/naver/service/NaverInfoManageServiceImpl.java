package com.won.StoreManageMent.naver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.common.jwt.AccountContext;
import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo;
import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo.*;
import com.won.StoreManageMent.naver.dto.ResponseCurrencyExchange;
import com.won.StoreManageMent.naver.entity.*;
import com.won.StoreManageMent.naver.repository.*;
import com.won.StoreManageMent.naver.vo.ExchangeCurrencyVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class NaverInfoManageServiceImpl implements NaverInfoManageService {

    @Autowired
    private DetailAttributeRepository detailAttributeRepository;

    @Autowired
    private DeliveryInfoRepository deliveryInfoRepository;

    @Autowired
    private ClaimDeliveryInfoRepository claimDeliveryInfoRepository;

    @Autowired
    private ProductInfoProvidedNoticeEtcRepository productInfoProvidedNoticeEtcRepository;

    @Autowired
    private ProductInfoProvidedNoticeRepository productInfoProvidedNoticeRepository;

    @Autowired
    private NaverSellerInfoRepository naverSellerInfoRepository;

    @Autowired
    private AccountContext accountContext;

    @Autowired
    private HttpClient httpClient;

    @Override
    @Transactional
    public String addNaverSellerInfo(RequestNaverSellerInfo requestNaverSellerInfo){

        ClaimDeliveryInfo requestClaimDeliveryInfo = requestNaverSellerInfo.getClaimDeliveryInfo();

        ClaimDeliveryInfoEntity claimDeliveryInfo = ClaimDeliveryInfoEntity.builder()
                .returnDeliveryCompanyPriorityType(requestClaimDeliveryInfo.getReturnDeliveryCompanyPriorityType())
                .returnDeliveryFee(requestClaimDeliveryInfo.getReturnDeliveryFee())
                .exchangeDeliveryFee(requestClaimDeliveryInfo.getExchangeDeliveryFee())
                .shippingAddressId(requestClaimDeliveryInfo.getShippingAddressId())
                .returnAddressId(requestClaimDeliveryInfo.getReturnAddressId())
                .freeReturnInsuranceYn(requestClaimDeliveryInfo.isFreeReturnInsuranceYn())
                .build();

        ClaimDeliveryInfoEntity resultClaimDeliveryInfo = claimDeliveryInfoRepository.save(claimDeliveryInfo);

        DeliveryInfo reqDeliveryInfo = requestNaverSellerInfo.getDeliveryInfo();

        DeliveryInfoEntity deliveryInfoEntity = DeliveryInfoEntity.builder()
                .deliveryType(reqDeliveryInfo.getDeliveryType())
                .deliveryAttributeType(reqDeliveryInfo.getDeliveryAttributeType())
                .deliveryCompany(reqDeliveryInfo.getDeliveryCompany())
                .deliveryBundleGroupUsable(reqDeliveryInfo.isDeliveryBundleGroupUsable())
                .deliveryFee(reqDeliveryInfo.getDeliveryFee())
                .claimDeliveryInfo(resultClaimDeliveryInfo)
                .installationFee(reqDeliveryInfo.isInstallationFee())
                .build();

        DeliveryInfoEntity deliveryInfo = deliveryInfoRepository.save(deliveryInfoEntity);

        Etc etc = requestNaverSellerInfo.getEtc();

        ProductInfoProvidedNoticeEtcEntity etcEntity =  ProductInfoProvidedNoticeEtcEntity.builder()
                .returnCostReason(etc.getReturnCostReason())
                .noRefundReason(etc.getNoRefundReason())
                .qualityAssuranceStandard(etc.getQualityAssuranceStandard())
                .compensationProcedure(etc.getCompensationProcedure())
                .troubleShootingContents(etc.getTroubleShootingContents())
                .certificateDetails(etc.getCertificateDetails())
                .itemName(etc.getItemName())
                .modelName(etc.getModelName())
                .manufacturer(etc.getManufacturer())
                .customerServicePhoneNumber(etc.getCustomerServicePhoneNumber())
                .build();

        ProductInfoProvidedNoticeEtcEntity resultEtc = productInfoProvidedNoticeEtcRepository.save(etcEntity);

        ProductInfoProvidedNoticeEntity productInfoProviderNoticeEntity = ProductInfoProvidedNoticeEntity.builder()
                .productInfoProvidedNoticeType("ETC")
                .productInfoProvidedNoticeEtc(resultEtc)
                .build();

        ProductInfoProvidedNoticeEntity productInfoProvidedNotic  = productInfoProvidedNoticeRepository.save(productInfoProviderNoticeEntity);

        DetailAttribute reqDetailAttribute = requestNaverSellerInfo.getDetailAttribute();

        DetailAttributeEntity detailAttributeEntity = DetailAttributeEntity.builder()
                .minorPurchasable(reqDetailAttribute.isMinorPurchasable())
                .afterServiceTelephoneNumber(reqDetailAttribute.getAfterServiceTelephoneNumber())
                .afterServiceGuideContent(reqDetailAttribute.getAfterServiceGuideContent())
                .originAreaCode(reqDetailAttribute.getOriginAreaCode())
                .kcExemptionType(reqDetailAttribute.getKcExemptionType())
                .kcCertifiedProductExclusionYn(reqDetailAttribute.getKcCertifiedProductExclusionYn())
                .productInfoProvidedNoticeEntity(productInfoProvidedNotic)
                .build();

        DetailAttributeEntity detailAttribute = detailAttributeRepository.save(detailAttributeEntity);

        NaverSellerInfoEntity naverSellerInfoEntity = NaverSellerInfoEntity.builder()
                .account(accountContext.getAccount())
                .deliveryInfo(deliveryInfo)
                .detailAttribute(detailAttribute)
                .build();

        NaverSellerInfoEntity naverSellerInfo = naverSellerInfoRepository.save(naverSellerInfoEntity);

        if(naverSellerInfo == null){
            throw new IllegalArgumentException();
        }

        return "성공적으로 등록되었습니다.";
    }

    @Override
    public ResponseCurrencyExchange getCurrencyExchange(String country){
        final int PAGE = 1;
        final int perPage = 1;

        String REQUEST_API = "https://finance.daum.net/api/exchanges/FRX.KRW" + country +
                "/days?symbolCode=FRX.KRW" + country +
                "&terms=days&page=" + PAGE + "&perPage=" + perPage;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(REQUEST_API))
                    .header("referer", "https://finance.daum.net/exchanges/FRX.KRW" + country)
                    .header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36")
                    .GET()
                    .build();

            String res = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();

            ObjectMapper objectMapper = new ObjectMapper();
            ExchangeCurrencyVo naverCategory = objectMapper.readValue(res, ExchangeCurrencyVo.class);

            double changePrice = Math.round(naverCategory.getData().get(0).getTtSellingPrice() / 100 * 100) / 100.0;

            return new ResponseCurrencyExchange(changePrice);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseCurrencyExchange(0);
        }
    }
}
