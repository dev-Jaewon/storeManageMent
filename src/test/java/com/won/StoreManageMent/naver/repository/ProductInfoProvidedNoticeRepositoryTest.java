package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.ProductInfoProvidedNoticeEntity;
import com.won.StoreManageMent.naver.entity.ProductInfoProvidedNoticeEtcEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductInfoProvidedNoticeRepositoryTest extends GenerateEntity {

    @Autowired
    private ProductInfoProvidedNoticeEtcRepository productInfoProvidedNoticeEtcRepository;

    @Autowired
    private ProductInfoProvidedNoticeRepository productInfoProvidedNoticeRepository;

    @Test
    @DisplayName("insertFromProductInfoProvidedNoticeTable")
    public void checkInsertDataFromProductInfoProvidedNotice(){
//        GIVEN
        ProductInfoProvidedNoticeEtcEntity etc = getProductInfoProvidedNoticeEtcEntity();

        ProductInfoProvidedNoticeEtcEntity etcResult = productInfoProvidedNoticeEtcRepository.save(etc);

        assertNotNull(etcResult);

        ProductInfoProvidedNoticeEntity productInfoProviderNotice = getProductInfoProvidedNoticeEntity(etcResult);

//        WHEN
        ProductInfoProvidedNoticeEntity resultProviderNotice = productInfoProvidedNoticeRepository.save(productInfoProviderNotice);

//        THEN
        assertNotNull(resultProviderNotice);
    }
}
