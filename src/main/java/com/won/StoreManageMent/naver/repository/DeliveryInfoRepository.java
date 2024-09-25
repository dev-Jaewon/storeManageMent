package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.DeliveryInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfoEntity, Long> {
}
