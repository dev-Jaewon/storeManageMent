package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.*;

public class GenerateEntity {

    public static ClaimDeliveryInfoEntity getClaimDeliveryInfo(){
        return ClaimDeliveryInfoEntity.builder()
                .returnDeliveryCompanyPriorityType("01")
                .returnDeliveryFee(3500)
                .exchangeDeliveryFee(3500)
                .shippingAddressId(1234)
                .build();
    }

    public static DeliveryInfoEntity getDeliveryInfoEntity(ClaimDeliveryInfoEntity resultClaimDeliveryInfo){
        return DeliveryInfoEntity.builder()
                .deliveryType("DELIVERY")
                .deliveryAttributeType("NORMAL")
                .deliveryCompany("HANJIN")
                .deliveryBundleGroupUsable(false)
                .deliveryFee("FREE")
                .claimDeliveryInfo(resultClaimDeliveryInfo)
                .installationFee(false)
                .build();
    }

    public static ProductInfoProvidedNoticeEtcEntity getProductInfoProvidedNoticeEtcEntity(){
        return ProductInfoProvidedNoticeEtcEntity.builder()
                .returnCostReason("")
                .noRefundReason("1")
                .qualityAssuranceStandard("1")
                .compensationProcedure("1")
                .troubleShootingContents("1")
                .certificateDetails("상세페이지 참조")
                .itemName("상세페이지 참조")
                .modelName("상세페이지 참조")
                .manufacturer("상세페이지 참조")
                .customerServicePhoneNumber("010-1234-5678")
                .build();
    }

    public static ProductInfoProvidedNoticeEntity getProductInfoProvidedNoticeEntity(ProductInfoProvidedNoticeEtcEntity etc){
        return ProductInfoProvidedNoticeEntity.builder()
                .productInfoProvidedNoticeType("ETC")
                .productInfoProvidedNoticeEtc(etc)
                .build();
    }

    public static DetailAttributeEntity getDetailAttributeEntity(ProductInfoProvidedNoticeEntity resultProviderNotice){
        return DetailAttributeEntity.builder()
                .minorPurchasable(true)
                .afterServiceTelephoneNumber("010-1234-5678")
                .afterServiceGuideContent("해외 구매대행 제품은 A/S 불가합니다.")
                .originAreaCode("03")
                .kcExemptionType("OVERSEAS")
                .kcCertifiedProductExclusionYn("KC_EXEMPTION_OBJECT")
                .productInfoProvidedNoticeEntity(resultProviderNotice)
                .build();
    }
}
