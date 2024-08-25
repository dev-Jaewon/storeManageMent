package com.won.StoreManageMent.coupang.dto;

import java.util.ArrayList;
import java.util.Map;

import com.won.StoreManageMent.coupang.dto.CoupangDto.ExchangeData.DeliveryInvoiceGroupDtos;
import com.won.StoreManageMent.coupang.dto.CoupangDto.ExchangeData.ExchangeAddressDtoV1;
import com.won.StoreManageMent.coupang.dto.CoupangDto.ExchangeData.ExchangeItemDtoV1s;

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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseExchangeInfo {
        private String type;
        private ArrayList<ExchangeData> data;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ExchangeInfo{
        private long code;
        private String message;
        private ArrayList<ExchangeData> data;
        private String nextToken;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ExchangeData {
        private long exchangeId;
        private long orderId;
        private String vendorId;
        private String orderDeliveryStatusCode;
        private String exchangeStatus;
        private String referType;
        private String faultType;
        private String exchangeAmount;
        private String reason;
        private String reasonCode;
        private String reasonCodeText;
        private String reasonEtcDetail;
        private String cancelReason;
        private String createdByType;
        private String createdAt;
        private String modifiedByType;
        private String modifiedAt;
        private ArrayList<ExchangeItemDtoV1s> exchangeItemDtoV1s;
        private ExchangeAddressDtoV1 exchangeAddressDtoV1;
        private DeliveryInvoiceGroupDtos deliveryInvoiceGroupDtos;
        private String deliveryStatus;
        private String collectStatus;
        private String collectCompleteDate;
        private CollectInformationsDto collectInformationsDto;
        private ReturnDeliveryDtos returnDeliveryDtos;
        private boolean successable;
        private String orderDeliveryStatusLabel;
        private String exchangeStatusLabel;
        private String referTypeLabel;
        private String faultTypeLabel;
        private String createdByTypeLabel;
        private boolean rejectable;
        private String modifiedByTypeLabel;
        private boolean deliveryInvoiceModifiable;

        @Getter
        @Setter
        public static class ReturnDeliveryDtos{
            private String deliveryCompanyCode;
            private String deliveryInvoiceNo;
        }

        @Getter
        @Setter
        public static class CollectInformationsDto{
            private String returnType;
            private String expectedReturnDate;
            private ArrayList<ReturndeliveryItemDtos> returndeliveryItemDtos;
            private ReturndeliveryDestinationDto returndeliveryDestinationDto;

            @Getter
            @Setter
            public static class ReturndeliveryItemDtos{
                private long vendorItemId;
                private String statusCode;
                private int returnCount;
                private String releaseStatus;
                private long paymentReturnDeliveryMapId;
                private long paymentItemId;
                private String modifiedBy;
                private String modifiedAt;
                private String createdBy;
                private String createdAt;
                private int count;
                private String confirmType;
                private String collectStatus;
            }

            @Getter
            @Setter
            public static class ReturndeliveryDestinationDto {
                private String vendorZipCode;
                private String vendorPhone;
                private String vendorName;
                private String vendorMobile;
                private String vendorAddressDetail;
                private String vendorAddress;
                private String safetyNumberStatus;
                private long safetyNumberId;
                private String safetyNumber;
                private long returnDeliveryId;
                private String returnCenterCode;
                private long receiptId;
                private String orderedByMobile;
                private long orderId;
                private String message;
                private String customerZipCode;
                private String customerPhone;
                private String customerName;
                private String customerMobile;
                private String customerAddressDetail;
                private String customerAddress;

            }
        }

        @Getter
        @Setter
        public static class ExchangeItemDtoV1s{
            private long exchangeItemId;
            private long orderItemId;
            private int orderItemUnitPrice;
            private String orderItemName;
            private long orderPackageId;
            private String orderPackageName;
            private long targetItemId;
            private int targetItemUnitPrice;
            private String targetItemName;
            private long targetPackageId;
            private String targetPackageName;
            private int quantity;
            private boolean orderItemDeliveryComplete;
            private boolean orderItemReturnComplete;
            private boolean targetItemDeliveryComplete;
            private String createdAt;
            private String modifiedAt;
            private long originalShipmentBoxId;
        }

        @Getter
        @Setter
        public static class ExchangeAddressDtoV1{
            private long exchangeAddressId;
            private String returnCustomerName;
            private String returnAddressZipCode;
            private String returnAddress;
            private String returnAddressDetail;
            private String returnPhone;
            private String returnMobile;
            private String returnMemo;
            private String deliveryCustomerName;
            private String deliveryAddressZipCode;
            private String deliveryAddress;
            private String deliveryAddressDetail;
            private String deliveryPhone;
            private String deliveryMobile;
            private String deliveryMemo;
            private String createdAt;
            private String modifiedAt;
            private long exchangeId;
        }

        @Getter
        @Setter
        public static class DeliveryInvoiceGroupDtos{
            private long shipmentBoxId;
            private int boxPrice;
            private long orderId;
            private String orderType;
            private String customerType;
            private String bundleType;
            private String extraMessage;        
            private String shippingDeliveryType;
            private ArrayList<DeliveryInvoiceDtos> deliveryInvoiceDtos;

            @Getter
            @Setter
            public static class DeliveryInvoiceDtos{
                private String invoiceNumber;
                private String estimatedDeliveryDate;
                private String deliveredDate;
                private String statusModifiedAt;
                private String invoiceNumberUploadDate;
                private String statusCode;
                private String deliverCode;
                private boolean isMainShipmentInvoice;
                private String parcelType;
                private ArrayList<InvoiceVendorItemDtos> invoiceVendorItemDtos;

                @Getter
                @Setter
                public static class InvoiceVendorItemDtos{
                    private long vendorItemId;
                    private int quantity;
                    private boolean hasAdditionalItem;
                    private String promiseDeliveryDate;
                    private String estimatedShippingDate;
                }
            }
        }
    }
}