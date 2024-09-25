package com.won.StoreManageMent.naver.repository;

import com.won.StoreManageMent.naver.entity.DetailAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailAttributeRepository extends JpaRepository<DetailAttributeEntity, Long> {
}
