package com.won.StoreManageMent.naver;

import com.won.StoreManageMent.auth.entity.AccountEntity;
import com.won.StoreManageMent.common.jwt.AccountContext;
import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo;
import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo.*;
import com.won.StoreManageMent.naver.dto.RequestProductList;
import com.won.StoreManageMent.naver.dto.ResponseProductList;
import com.won.StoreManageMent.naver.dto.ResponseTags;
import com.won.StoreManageMent.naver.entity.*;
import com.won.StoreManageMent.naver.repository.*;
import com.won.StoreManageMent.naver.service.NaverApiServiceImpl;
import com.won.StoreManageMent.naver.service.NaverInfoManageServiceImpl;
import com.won.StoreManageMent.naver.vo.NaverTagsVo;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.*;

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
    private ProductRepository productRepository;

    @Mock
    private AccountContext accountContext;

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private NaverInfoManageServiceImpl naverInfoService;

    @InjectMocks
    private NaverApiServiceImpl naverApiService;

    @Test
    public void checkUpdateDeliveryInfoMethod(){
        RequestNaverSellerInfo naverSellerInfo = NaverRequestParam.getRequestNaverSellerInfo();

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

    @Test
    public void checkProductList(){

        RequestProductList requestProductList = RequestProductList.builder()
                .page(0)
                .size(10)
                .titleKeyword("asdf")
                .build();

        Pageable pageable = PageRequest.of(
                requestProductList.getPage(),
                requestProductList.getSize(),
                Sort.by(
                        Sort.Order.desc("id")
                ));

        AccountEntity account = AccountEntity.builder()
                .id(0L)
                .platform("naver")
                .platformId("012345")
                .nickName("testMan")
                .profileImage("http://abcde")
                .build();

        given(accountContext.getAccount()).willReturn(account);

        ProductEntity productInfo = ProductEntity.builder()
                .id(1)
                .brand("sams")
                .title("컴퓨터")
                .price(10000)
                .image("http://image")
                .detailText("detailInfo")
                .category("categoryInfo")
                .tags(Arrays.asList("tag1", "tag2"))
                .createat(LocalDateTime.now())
                .updateat(LocalDateTime.now())
                .linkStore("linkStore")
                .linkProduct("linkProduct")
                .incomPrice("1000")
                .account(account)
                .originPrice(1000)
                .currency("currency")
                .weight(100.1)
                .crawlingOptionColor("crawlingOptionColor")
                .originProductNo("originProductNo")
                .build();

        Page<ProductEntity> productPage = new PageImpl<>(Arrays.asList(productInfo), pageable, pageable.getPageSize());

        given(productRepository.findByAccount(account, pageable)).willReturn(productPage);

//        WHEN
        ResponseProductList result = naverApiService.productList(requestProductList);

//        THEN
        assertNotNull(result);
        assertSame(productPage.getContent().size(), result.getList().size());
    }

    @Test
    public void getSearchTagsService()throws Exception{
//        GIVEN
        String keyword = "자전거";

        List<NaverTagsVo> apiResult = Arrays.asList(new NaverTagsVo(1, "testTag"));
        String resposneDat = JSONArray.toJSONString(apiResult);

        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        given(httpClient.send(any(), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).willReturn(httpResponse);
        given(httpResponse.body()).willReturn(resposneDat);


//        WHEN
        ResponseTags result = naverApiService.searchTags(keyword);

//        THEN
        assertSame(result.getTags().size(), apiResult.size());
    }
}
