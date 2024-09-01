package com.won.StoreManageMent.exchangeRate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_price")
public class DeliveryPriceEntity {
    
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String kg;

    @Column(nullable = false)
    private String price;

}
