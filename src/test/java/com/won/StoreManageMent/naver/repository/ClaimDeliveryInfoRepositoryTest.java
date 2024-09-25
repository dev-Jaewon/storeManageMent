package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.ClaimDeliveryInfoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClaimDeliveryInfoRepositoryTest {

    @Autowired
    private ClaimDeliveryInfoRepository claimDeliveryInfoRepository;

    @Test
    @DisplayName("insertFromClaimDeliveryInfoTable")
    public void checkInsertDataFromClaimDeliveryInfo(){
//        GIVEN
        ClaimDeliveryInfoEntity testClaimDeliveryInfo = ClaimDeliveryInfoEntity.builder()
                .returnDeliveryCompanyPriorityType("01")
                .returnDeliveryFee(3500)
                .exchangeDeliveryFee(3500)
                .shippingAddressId(1234)
                .build();


//        WHEN
        ClaimDeliveryInfoEntity result = claimDeliveryInfoRepository.save(testClaimDeliveryInfo);

//        THEN
        assertNotNull(result, "no generated data from table claimDeliveryInfoTable");
    }

}
