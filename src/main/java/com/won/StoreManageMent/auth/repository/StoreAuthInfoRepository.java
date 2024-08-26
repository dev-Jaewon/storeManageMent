package com.won.StoreManageMent.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.won.StoreManageMent.auth.entity.StoreAuthInfoEntity;

public interface StoreAuthInfoRepository extends JpaRepository<StoreAuthInfoEntity, Long> {
    
}
