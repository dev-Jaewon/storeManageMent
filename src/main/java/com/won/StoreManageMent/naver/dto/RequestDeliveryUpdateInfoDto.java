package com.won.StoreManageMent.naver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDeliveryUpdateInfoDto {
    private String deliveryType;
    private String deliveryAttributeType;
    private String deliveryCompany;
    private boolean deliveryBundleGroupUsable;
    private String deliveryFee;
    private String returnDeliveryCompanyPriorityType;
    private int returnDeliveryFee;
    private int exchangeDeliveryFee;
    private long shippingAddressId;
    private long returnAddressId;
    private boolean freeReturnInsuranceYn;
    private boolean installationFee;
}
