package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.auth.entity.AccountEntity;
import com.won.StoreManageMent.naver.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    public Page<ProductEntity> findByAccount(AccountEntity account, Pageable pageable);
}
