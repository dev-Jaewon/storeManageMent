package com.won.StoreManageMent.naver.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//=====================================================
//  Naver Upload Default Const Info
//=====================================================

@Getter
@Setter
@Builder
public class NaverUploadVo {
    private OriginProduct originProduct;
    private SmartstoreChannelProduct smartstoreChannelProduct;

    @Getter
    @Setter
    public static class SmartstoreChannelProduct {
        private final boolean naverShoppingRegistration = true;
        private final String channelProductDisplayStatusType = "ON";
    }

    @Getter
    @Setter
    public static class OriginProduct {
        private int statusType;
        private String name;
        private String leafCategoryId;
        private String detailContent;
        private Images images;
        private String salePrice;
        private int stockQuantity;
        private DeliveryInfo deliveryInfo;
        private DetailAttribute detailAttribute;
        private CustomerBenefit customerBenefit;

        @Getter
        @Setter
        public static class CustomerBenefit {
            private ImmediateDiscountPolicy immediateDiscountPolicy;

            @Getter
            @Setter
            public static class ImmediateDiscountPolicy {
                private DiscountMethod discountMethod;

                @Getter
                @Setter
                public static class DiscountMethod {
                    private int discountMethod;
                    private String unitType;
                }
            }
        }

        @Getter
        @Setter
        public static class DetailAttribute {
            private OptionInfo optionInfo;
            private NaverShoppingSearchInfo naverShoppingSearchInfo;
            private AfterServiceInfo afterServiceInfo;
            private OriginAreaInfo originAreaInfo;
            private boolean minorPurchasable;
            private CertificationTargetExcludeContent certificationTargetExcludeContent;
            private ProductInfoProvidedNotice productInfoProvidedNotice;
            private SeoInfo seoInfo;

            @Getter
            @Setter
            public static class SeoInfo {
                private String pageTitle;
                private String metaDescription;
                private List<SellerTags> sellerTags;

                @Getter
                @Setter
                public static class SellerTags {
                    private long code;
                    private String text;
                }
            }

            @Getter
            @Setter
            public static class ProductInfoProvidedNotice {
                private String productInfoProvidedNoticeType;
                private Etc etc;

                @Getter
                @Setter
                public static  class Etc {
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
            }

            @Getter
            @Setter
            public static class CertificationTargetExcludeContent {
                private String kcExemptionType;
                private String kcCertifiedProductExclusionYn;
            }


            @Getter
            @Setter
            public static class OriginAreaInfo {
                private String originAreaCode;
            }

            @Getter
            @Setter
            public static class AfterServiceInfo {
                private String afterServiceTelephoneNumber;
                private String afterServiceGuideContent;
            }

            @Getter
            @Setter
            public static class NaverShoppingSearchInfo {
                private String brandName;
                private String manufacturerName;
            }

            @Getter
            @Setter
            public static class OptionInfo {
                private List<OptionSimple> optionSimple;

                @Getter
                @Setter
                @AllArgsConstructor
                public static class OptionSimple {
                    private String groupName;
                    private String value;
                }
            }
        }



        @Getter
        @Setter
        public static class DeliveryInfo {
            private String deliveryType;
            private String deliveryAttributeType;
            private String deliveryCompany;
            private boolean deliveryBundleGroupUsable;
            private DeliveryFee deliveryFee;
            private ClaimDeliveryInfo claimDeliveryInfo;
            private boolean installationFee;

            @Getter
            @Setter
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
            public static class DeliveryFee {
                private String deliveryFeeType;
            }

        }


        @Getter
        @Setter
        public static class Images {
            private List<String> optionalImages;
            private RepresentativeImage representativeImage;

            @Getter
            @Setter
            public static class RepresentativeImage {
                private String url;
            }
        }
    }
}
