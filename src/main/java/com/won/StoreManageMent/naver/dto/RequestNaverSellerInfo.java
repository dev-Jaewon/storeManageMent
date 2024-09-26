package com.won.StoreManageMent.naver.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestNaverSellerInfo {
    private ClaimDeliveryInfo claimDeliveryInfo;
    private DeliveryInfo deliveryInfo;
    private Etc etc;
    private DetailAttribute detailAttribute;

    @Getter
    @Setter
    @Builder
    public static class ClaimDeliveryInfo {
        private String returnDeliveryCompanyPriorityType;
        private int returnDeliveryFee;
        private int exchangeDeliveryFee;
        private long shippingAddressId;
        private long returnAddressId;
        private boolean freeReturnInsuranceYn;
    }

    @Getter
    @Setter
    @Builder
    public static class DeliveryInfo {
        private String deliveryType;
        private String deliveryAttributeType;
        private String deliveryCompany;
        private String deliveryFee;
        private boolean deliveryBundleGroupUsable;
        private boolean installationFee;
    }

    @Getter
    @Setter
    @Builder
    public static class Etc {
        private String returnCostReason;
        private String noRefundReason;
        private String qualityAssuranceStandard;
        private String compensationProcedure;
        private String troubleShootingContents;
        private String certificateDetails;
        private String itemName;
        private String modelName;
        private String manufacturer;
        private String customerServicePhoneNumber;
    }

    @Getter
    @Setter
    @Builder
    public static class DetailAttribute {
        private boolean minorPurchasable;
        private String afterServiceTelephoneNumber;
        private String afterServiceGuideContent;
        private String originAreaCode;
        private String kcExemptionType;
        private String kcCertifiedProductExclusionYn;
    }

}
