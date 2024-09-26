package com.won.StoreManageMent.naver.service;

import com.won.StoreManageMent.common.jwt.AccountContext;
import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo;
import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo.*;
import com.won.StoreManageMent.naver.entity.*;
import com.won.StoreManageMent.naver.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
