package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.auth.entity.AccountEntity;
import com.won.StoreManageMent.auth.repository.AccountRepository;
import com.won.StoreManageMent.naver.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NaverSellerInfoRepositoryTest extends GenerateEntity {

    @Autowired
    private AccountRepository accountRepository;

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

    @Test
    public void checkInsertDataFromNaverSellerInfo(){
//        GIVEN
//        ======= USER 생성
        AccountEntity account =  getAccountInfo();

        AccountEntity resultAccount = accountRepository.save(account);

//        ======= Delivery Info 생성
        ClaimDeliveryInfoEntity testClaimDeliveryInfo = getClaimDeliveryInfo();

        ClaimDeliveryInfoEntity resultClaimDeliveryInfo = claimDeliveryInfoRepository.save(testClaimDeliveryInfo);

        assertNotNull(resultClaimDeliveryInfo, "no generated data from claimDeliveryInfo table");

        DeliveryInfoEntity deliveryInfo = getDeliveryInfoEntity(resultClaimDeliveryInfo);

        DeliveryInfoEntity resultDeliveryInfo = deliveryInfoRepository.save(deliveryInfo);

//        ======= Detail Attribute 생성


        ProductInfoProvidedNoticeEtcEntity etc = getProductInfoProvidedNoticeEtcEntity();

        ProductInfoProvidedNoticeEtcEntity etcResult = productInfoProvidedNoticeEtcRepository.save(etc);

        ProductInfoProvidedNoticeEntity productInfoProviderNotice = getProductInfoProvidedNoticeEntity(etcResult);

        ProductInfoProvidedNoticeEntity resultProviderNotice = productInfoProvidedNoticeRepository.save(productInfoProviderNotice);

        DetailAttributeEntity detailAttribute = getDetailAttributeEntity(resultProviderNotice);

        DetailAttributeEntity resultDetailAttribute = detailAttributeRepository.save(detailAttribute);

        NaverSellerInfoEntity naverSellerInfo = NaverSellerInfoEntity.builder()
                .account(resultAccount)
                .deliveryInfo(resultDeliveryInfo)
                .detailAttribute(resultDetailAttribute)
                .build();;

//        WHEN
        NaverSellerInfoEntity resultnaverSellerInfo = naverSellerInfoRepository.save(naverSellerInfo);

//        WHEN
        assertNotNull(resultnaverSellerInfo);




    }
}
