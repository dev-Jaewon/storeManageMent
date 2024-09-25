package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.ProductInfoProvidedNoticeEtcEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoProvidedNoticeEtcRepository extends JpaRepository<ProductInfoProvidedNoticeEtcEntity, Long>{
}
