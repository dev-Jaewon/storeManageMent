package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.DetailAttributeEntity;
import com.won.StoreManageMent.naver.entity.ProductInfoProvidedNoticeEntity;
import com.won.StoreManageMent.naver.entity.ProductInfoProvidedNoticeEtcEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetailAttributeRepositoryTest {

    @Autowired
    private ProductInfoProvidedNoticeEtcRepository productInfoProvidedNoticeEtcRepository;

    @Autowired
    private ProductInfoProvidedNoticeRepository productInfoProvidedNoticeRepository;

    @Autowired
    private DetailAttributeRepository detailAttributeRepository;

    @Test
    public void InsertDataFromDetailAttribute(){
//        GIVEN
        ProductInfoProvidedNoticeEtcEntity etc = ProductInfoProvidedNoticeEtcEntity.builder()
                .returnCostReason("1")
                .noRefundReason("1")
                .qualityAssuranceStandard("1")
                .compensationProcedure("1")
                .troubleShootingContents("1")
                .certificateDetails("상세페이지 참조")
                .itemName("상세페이지 참조")
                .modelName("상세페이지 참조")
                .manufacturer("상세페이지 참조")
                .customerServicePhoneNumber("010-1234-5678")
                .build();

        ProductInfoProvidedNoticeEtcEntity etcResult = productInfoProvidedNoticeEtcRepository.save(etc);

        assertNotNull(etcResult);

        ProductInfoProvidedNoticeEntity productInfoProviderNotice = ProductInfoProvidedNoticeEntity.builder()
                .productInfoProvidedNoticeType("ETC")
                .productInfoProvidedNoticeEtc(etcResult)
                .build();

        ProductInfoProvidedNoticeEntity resultProviderNotice = productInfoProvidedNoticeRepository.save(productInfoProviderNotice);


        DetailAttributeEntity detailAttribute = DetailAttributeEntity.builder()
                .minorPurchasable(true)
                .afterServiceTelephoneNumber("010-1234-5678")
                .afterServiceGuideContent("해외 구매대행 제품은 A/S 불가합니다.")
                .originAreaCode("03")
                .kcExemptionType("OVERSEAS")
                .kcCertifiedProductExclusionYn("KC_EXEMPTION_OBJECT")
                .productInfoProvidedNoticeEntity(resultProviderNotice)
                .build();

//        WHEN
        DetailAttributeEntity resultDetailAttribute = detailAttributeRepository.save(detailAttribute);

//        THEN
        assertNotNull(resultDetailAttribute);

    }

}
