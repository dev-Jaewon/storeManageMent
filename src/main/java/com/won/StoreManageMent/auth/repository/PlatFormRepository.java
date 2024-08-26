package com.won.StoreManageMent.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.won.StoreManageMent.auth.entity.PlatFormEntity;

@Repository
public interface PlatFormRepository extends JpaRepository<PlatFormEntity, Long> {
    
    public PlatFormEntity findByName(String name);
}
