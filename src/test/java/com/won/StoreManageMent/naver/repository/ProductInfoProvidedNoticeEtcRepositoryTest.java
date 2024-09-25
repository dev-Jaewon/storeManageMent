package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.ProductInfoProvidedNoticeEtcEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductInfoProvidedNoticeEtcRepositoryTest extends GenerateEntity {

    @Autowired
    private ProductInfoProvidedNoticeEtcRepository productInfoProvidedNoticeEtcRepoy;

    @Test
    public void insertProductInfoProvidedNoticeEtcRepository(){
//        GIVEN
        ProductInfoProvidedNoticeEtcEntity etc = getProductInfoProvidedNoticeEtcEntity();

//        WHEN
        ProductInfoProvidedNoticeEtcEntity result = productInfoProvidedNoticeEtcRepoy.save(etc);

//        THEN
        assertNotNull(result);
    }
}
