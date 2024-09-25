package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.ClaimDeliveryInfoEntity;
import com.won.StoreManageMent.naver.entity.DeliveryInfoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeliveryInfoRepositoryTest extends GenerateEntity {

    @Autowired
    private ClaimDeliveryInfoRepository claimDeliveryInfoRepository;

    @Autowired
    private DeliveryInfoRepository deliveryInfoRepository;

    @Test
    @DisplayName("insertFromDeliveryInfoTable")
    public void checkInsertDataFromDeliveryInfo(){
//        GiVEN
        ClaimDeliveryInfoEntity testClaimDeliveryInfo = getClaimDeliveryInfo();

        ClaimDeliveryInfoEntity resultClaimDeliveryInfo = claimDeliveryInfoRepository.save(testClaimDeliveryInfo);

        assertNotNull(resultClaimDeliveryInfo, "no generated data from claimDeliveryInfo table");

        DeliveryInfoEntity deliveryInfo = getDeliveryInfoEntity(resultClaimDeliveryInfo);

//        WHEN
        DeliveryInfoEntity result = deliveryInfoRepository.save(deliveryInfo);

//        THEN
        assertNotNull(result, "no generated data from deliveryInfo table");
    }

}
