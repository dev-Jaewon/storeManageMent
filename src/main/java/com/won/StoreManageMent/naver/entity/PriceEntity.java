package com.won.StoreManageMent.naver.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "price")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "exchange_currency", length = 10, nullable = true)
    private String exchangeCurrency;

    @Column(name = "exchange_currency_price", nullable = false)
    private double exchangeCurrencyPrice;

    @Column(name = "origin_price", nullable = false)
    private double originPrice;

    @Column(name = "krw_price", nullable = false)
    private int krwPrice;

    @Column(name = "margin", nullable = false)
    private int margin;

    @Column(name = "profit", nullable = false)
    private int profit;

}
