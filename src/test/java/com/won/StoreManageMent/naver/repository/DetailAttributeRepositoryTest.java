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
public class DetailAttributeRepositoryTest extends GenerateEntity {

    @Autowired
    private ProductInfoProvidedNoticeEtcRepository productInfoProvidedNoticeEtcRepository;

    @Autowired
    private ProductInfoProvidedNoticeRepository productInfoProvidedNoticeRepository;

    @Autowired
    private DetailAttributeRepository detailAttributeRepository;

    @Test
    public void InsertDataFromDetailAttribute(){
//        GIVEN
        ProductInfoProvidedNoticeEtcEntity etc = getProductInfoProvidedNoticeEtcEntity();

        ProductInfoProvidedNoticeEtcEntity etcResult = productInfoProvidedNoticeEtcRepository.save(etc);

        assertNotNull(etcResult);

        ProductInfoProvidedNoticeEntity productInfoProviderNotice = getProductInfoProvidedNoticeEntity(etcResult);

        ProductInfoProvidedNoticeEntity resultProviderNotice = productInfoProvidedNoticeRepository.save(productInfoProviderNotice);


        DetailAttributeEntity detailAttribute = getDetailAttributeEntity(resultProviderNotice);

//        WHEN
        DetailAttributeEntity resultDetailAttribute = detailAttributeRepository.save(detailAttribute);

//        THEN
        assertNotNull(resultDetailAttribute);

    }

}
