package com.won.StoreManageMent.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.won.StoreManageMent.auth.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    public AccountEntity findByPlatformId(String platformId);
}
