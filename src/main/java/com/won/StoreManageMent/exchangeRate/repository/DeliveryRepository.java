package com.won.StoreManageMent.exchangeRate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.won.StoreManageMent.exchangeRate.entity.DeliveryPriceEntity;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryPriceEntity, Long>{

    @Query(value = "SELECT * FROM delivery_price d WHERE (d.country = :country AND d.kg = :kg)", nativeQuery = true)
    DeliveryPriceEntity findByCountryAndKg(@Param("country") String country, @Param("kg") String kg);
}