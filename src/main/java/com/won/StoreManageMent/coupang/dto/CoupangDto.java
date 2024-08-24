package com.won.StoreManageMent.coupang.dto;

import java.util.ArrayList;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CoupangDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseOrderInfo {
        private String type;
        private ArrayList<OrderData> data;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OrderInfo{
        private long code;
        private String message;
        private ArrayList<OrderData> data;
        private String nextToken;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OrderData {
        private long shipmentBoxId;
        private long orderId;
        private String orderedAt;
        private Orderer orderer;
        private String paidAt;
        private String status;
        private int shippingPrice;
        private int remotePrice;
        private boolean remoteArea;
        private String parcelPrintMessage;
        private boolean splitShipping;
        private boolean ableSplitShipping;
        private Receiver receiver;
        private ArrayList<OrderItems> orderItems;
        private OverseaShippingInfoDto overseaShippingInfoDto;
        private String deliveryCompanyName;
        private String invoiceNumber;
        private String inTrasitDateTime;
        private String deliveredDate;
        private String refer;
        private String shipmentType;
    }


    @Getter
    @Setter
    public static class Orderer {
        private String name;
        private String email;
        private String safeNumber;
        private int ordererNumber;
    }

    @Getter
    @Setter
    public static class Receiver {
        private String name;
        private String safeNumber;
        private String receiverNumber;
        private String addr1;
        private String addr2;
        private String postCode;
    }

    @Getter
    @Setter
    public static class OrderItems {
        private long vendorItemPackageId;
        private String vendorItemPackageName;
        private long productId;
        private long vendorItemId;
        private String vendorItemName;
        private int shippingCount;
        private int salesPrice;
        private int orderPrice;
        private int discountPrice;
        private int instantCouponDiscount;
        private int downloadableCouponDiscount;
        private int coupangDiscount;
        private String externalVendorSkuCode;
        private String etcInfoHeader;
        private String etcInfoValue;
        private String etcInfoValues;
        private long sellerProductId;
        private String sellerProductName;
        private String sellerProductItemName;
        private String firstSellerProductItemName;
        private int cancelCount;
        private int holdCountForCancel;
        private String estimatedShippingDate;
        private String plannedShippingDate;
        private String invoiceNumberUploadDate;
        private Map<String, Object> extraProperties;
        private boolean pricingBadge;
        private boolean usedProduct;
        private String confirmDate;
        private String deliveryChargeTypeName;
        private String upBundleVendorItemId;
        private String upBundleVendorItemName;
        private String upBundleSize;
        private boolean canceled;
        private boolean upBundleItem;
    }

    @Getter
    @Setter
    public static class OverseaShippingInfoDto{
        private String personalCustomsClearanceCode;
        private String ordererSsn;
        private String ordererPhoneNumber;
    }
}