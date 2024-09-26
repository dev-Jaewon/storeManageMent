package com.won.StoreManageMent.naver;

import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo;

public class NaverRequestParam {

    public static RequestNaverSellerInfo getRequestNaverSellerInfo(){
        RequestNaverSellerInfo.ClaimDeliveryInfo claimDeliveryInfo = RequestNaverSellerInfo.ClaimDeliveryInfo.builder()
                .returnDeliveryCompanyPriorityType("01")
                .returnDeliveryFee(100)
                .exchangeDeliveryFee(1002)
                .shippingAddressId(1003)
                .returnAddressId(1004)
                .freeReturnInsuranceYn(true)
                .build();

        RequestNaverSellerInfo.DeliveryInfo deliveryInfo = RequestNaverSellerInfo.DeliveryInfo.builder()
                .deliveryType("DELIVERY")
                .deliveryAttributeType("NORMAL")
                .deliveryCompany("HANJIN")
                .deliveryBundleGroupUsable(false)
                .deliveryFee("FREE")
                .installationFee(false)
                .build();

        RequestNaverSellerInfo.Etc etc = RequestNaverSellerInfo.Etc.builder()
                .returnCostReason("001")
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

        RequestNaverSellerInfo.DetailAttribute detailAttribute = RequestNaverSellerInfo.DetailAttribute.builder()
                .minorPurchasable(true)
                .afterServiceTelephoneNumber("010-1234-5678")
                .afterServiceGuideContent("해외 구매대행 제품은 A/S 불가합니다.")
                .originAreaCode("03")
                .kcExemptionType("OVERSEAS")
                .kcCertifiedProductExclusionYn("KC_EXEMPTION_OBJECT")
                .build();

        return RequestNaverSellerInfo.builder()
                .claimDeliveryInfo(claimDeliveryInfo)
                .deliveryInfo(deliveryInfo)
                .etc(etc)
                .detailAttribute(detailAttribute)
                .build();
    }
}
