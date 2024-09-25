package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.ProductInfoProvidedNoticeEtcEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductInfoProvidedNoticeEtcRepositoryTest {

    @Autowired
    private ProductInfoProvidedNoticeEtcRepository productInfoProvidedNoticeEtcRepoy;

    @Test
    public void insertProductInfoProvidedNoticeEtcRepository(){
//        GIVEN
        ProductInfoProvidedNoticeEtcEntity etc = ProductInfoProvidedNoticeEtcEntity.builder()
                .returnCostReason("")
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

//        WHEN
        ProductInfoProvidedNoticeEtcEntity result = productInfoProvidedNoticeEtcRepoy.save(etc);

//        THEN
        assertNotNull(result);
    }
}
