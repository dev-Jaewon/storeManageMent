package com.won.StoreManageMent.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.won.StoreManageMent.auth.entity.PlatformInfoEntity;

public interface PlatFormInfoRepository extends JpaRepository<PlatformInfoEntity, Long> {
    
}
