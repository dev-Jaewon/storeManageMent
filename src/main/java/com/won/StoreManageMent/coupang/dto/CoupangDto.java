package com.won.StoreManageMent.coupang.dto;

import java.util.ArrayList;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CoupangDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseCoupangInfo {
        private ResponseOrderInfo order;
    }

    

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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseReturnInfo {
        private String type;
        private ArrayList<ReturnData> data;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReturnInfo {
        private long code;
        private String message;
        private ArrayList<ReturnData> data;
        private String nextToken;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ReturnData {
        private long receiptId;
        private long orderId;
        private long paymentId;
        private String receiptType;
        private String receiptStatus;
        private String createdAt;
        private String modifiedAt;
        private String requesterName;
        private String requesterPhoneNumber;
        private String requesterRealPhoneNumber;
        private String requesterAddress;
        private String requesterAddressDetail;
        private String requesterZipCode;
        private String cancelReasonCategory1;
        private String cancelReasonCategory2;
        private int cancelCountSum;
        private long returnDeliveryId;
        private String returnDeliveryType;
        private String releaseStopStatus;
        private int enclosePrice;
        private String faultByType;
        private boolean preRefund;
        private String completeConfirmType;
        private String completeConfirmDate;
        private ArrayList<ReturnItem> returnItems;
        private ArrayList<ReturnDeliveryDtos> returnDeliveryDtos;
        private String reasonCode;
        private String reasonCodeText;
        private long returnShippingCharge;
        private String nextToken;

        @Getter
        @Setter
        public static class ReturnItem {
            private long vendorItemPackageId;
            private String vendorItemPackageName;
            private long vendorItemId;
            private String vendorItemName;
            private int cancelCount;
            private int purchaseCount;
            private long shipmentBoxId;
            private long sellerProductId;
            private String sellerProductName;
            private String releaseStatus;
            private String cancelCompleteUser;
        }

        @Getter
        @Setter
        public static class ReturnDeliveryDtos {
            private String deliveryCompanyCode;
            private String deliveryInvoiceNo;
        }
    }
}