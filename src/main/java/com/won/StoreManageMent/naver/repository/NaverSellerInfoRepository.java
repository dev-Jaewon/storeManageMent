package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.NaverSellerInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaverSellerInfoRepository extends JpaRepository<NaverSellerInfoEntity, Long> {
}
