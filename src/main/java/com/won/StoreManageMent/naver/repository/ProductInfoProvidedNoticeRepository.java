package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.ProductInfoProvidedNoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoProvidedNoticeRepository extends JpaRepository<ProductInfoProvidedNoticeEntity, Long> {
}
