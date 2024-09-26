package com.won.StoreManageMent.naver;

import com.won.StoreManageMent.auth.entity.AccountEntity;
import com.won.StoreManageMent.common.jwt.AccountContext;
import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo;
import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo.*;
import com.won.StoreManageMent.naver.entity.*;
import com.won.StoreManageMent.naver.repository.*;
import com.won.StoreManageMent.naver.service.NaverInfoManageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NaverServiceTest {

    @Mock
    private ClaimDeliveryInfoRepository claimDeliveryInfoRepository;

    @Mock
    private DeliveryInfoRepository deliveryInfoRepository;

    @Mock
    private ProductInfoProvidedNoticeEtcRepository productInfoProvidedNoticeEtcRepository;

    @Mock
    private DetailAttributeRepository detailAttributeRepository;

    @Mock
    private NaverSellerInfoRepository naverSellerInfoRepository;

    @Mock
    private ProductInfoProvidedNoticeRepository productInfoProvidedNoticeRepository;

    @Mock
    private AccountContext accountContext;

    @InjectMocks
    private NaverInfoManageServiceImpl naverInfoService;

    @BeforeEach
    public void setRequestNaverSellerInfo(){
        naverSellerInfo = NaverRequestParam.getRequestNaverSellerInfo();
    }

    private RequestNaverSellerInfo naverSellerInfo;

    @Test
    public void checkUpdateDeliveryInfoMethod(){
        ClaimDeliveryInfo requestClaimDeliveryInfo = naverSellerInfo.getClaimDeliveryInfo();

        ClaimDeliveryInfoEntity claimDeliveryInfo = ClaimDeliveryInfoEntity.builder()
                .id(1)
                .returnDeliveryCompanyPriorityType(requestClaimDeliveryInfo.getReturnDeliveryCompanyPriorityType())
                .returnDeliveryFee(requestClaimDeliveryInfo.getReturnDeliveryFee())
                .exchangeDeliveryFee(requestClaimDeliveryInfo.getExchangeDeliveryFee())
                .shippingAddressId(requestClaimDeliveryInfo.getShippingAddressId())
                .returnAddressId(requestClaimDeliveryInfo.getReturnAddressId())
                .freeReturnInsuranceYn(requestClaimDeliveryInfo.isFreeReturnInsuranceYn())
                .build();

        when(claimDeliveryInfoRepository.save(any(ClaimDeliveryInfoEntity.class))).thenReturn(claimDeliveryInfo);

        DeliveryInfo reqDeliveryInfo = naverSellerInfo.getDeliveryInfo();

        DeliveryInfoEntity deliveryInfo = DeliveryInfoEntity.builder()
                .id(1)
                .deliveryType(reqDeliveryInfo.getDeliveryType())
                .deliveryAttributeType(reqDeliveryInfo.getDeliveryAttributeType())
                .deliveryCompany(reqDeliveryInfo.getDeliveryCompany())
                .deliveryBundleGroupUsable(reqDeliveryInfo.isDeliveryBundleGroupUsable())
                .deliveryFee(reqDeliveryInfo.getDeliveryFee())
                .claimDeliveryInfo(claimDeliveryInfo)
                .installationFee(reqDeliveryInfo.isInstallationFee())
                .build();

        when(deliveryInfoRepository.save(any(DeliveryInfoEntity.class))).thenReturn(deliveryInfo);

        Etc etc = naverSellerInfo.getEtc();

        ProductInfoProvidedNoticeEtcEntity productInfoProvidedNoticeEtc = ProductInfoProvidedNoticeEtcEntity.builder()
                .id(1)
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

        when(productInfoProvidedNoticeEtcRepository.save(any(ProductInfoProvidedNoticeEtcEntity.class))).thenReturn(productInfoProvidedNoticeEtc);

        ProductInfoProvidedNoticeEntity productInfoProviderNotice = ProductInfoProvidedNoticeEntity.builder()
                .id(1)
                .productInfoProvidedNoticeType("ETC")
                .productInfoProvidedNoticeEtc(productInfoProvidedNoticeEtc)
                .build();

        when(productInfoProvidedNoticeRepository.save(any(ProductInfoProvidedNoticeEntity.class))).thenReturn(productInfoProviderNotice);

        DetailAttribute reqDetailAttribute = naverSellerInfo.getDetailAttribute();

        DetailAttributeEntity detailAttribute = DetailAttributeEntity.builder()
                .id(1)
                .minorPurchasable(reqDetailAttribute.isMinorPurchasable())
                .afterServiceTelephoneNumber(reqDetailAttribute.getAfterServiceTelephoneNumber())
                .afterServiceGuideContent(reqDetailAttribute.getAfterServiceGuideContent())
                .originAreaCode(reqDetailAttribute.getOriginAreaCode())
                .kcExemptionType(reqDetailAttribute.getKcExemptionType())
                .kcCertifiedProductExclusionYn(reqDetailAttribute.getKcCertifiedProductExclusionYn())
                .productInfoProvidedNoticeEntity(productInfoProviderNotice)
                .build();

        when(detailAttributeRepository.save(any(DetailAttributeEntity.class))).thenReturn(detailAttribute);

        AccountEntity account = AccountEntity.builder()
                .id(0L)
                .platform("naver")
                .platformId("012345")
                .nickName("testMan")
                .profileImage("http://abcde")
                .build();

        when(accountContext.getAccount()).thenReturn(account);

        NaverSellerInfoEntity naverSellerInfoEntity = NaverSellerInfoEntity.builder()
                .id(1)
                .account(account)
                .deliveryInfo(deliveryInfo)
                .detailAttribute(detailAttribute)
                .build();

        when(naverSellerInfoRepository.save(any(NaverSellerInfoEntity.class))).thenReturn(naverSellerInfoEntity);

//      WHEN
        String result = naverInfoService.addNaverSellerInfo(naverSellerInfo);

//      Then
        assertSame(result, "성공적으로 등록되었습니다.");
    }
}
