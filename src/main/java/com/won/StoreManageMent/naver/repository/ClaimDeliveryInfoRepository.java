package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.ClaimDeliveryInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimDeliveryInfoRepository extends JpaRepository<ClaimDeliveryInfoEntity, Long> {
}
