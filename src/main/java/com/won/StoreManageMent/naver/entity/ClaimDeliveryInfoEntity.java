package com.won.StoreManageMent.naver.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "claim_delivery_info")
@Builder
public class ClaimDeliveryInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "return_delivery_company_priority_type", nullable = false)
    private String returnDeliveryCompanyPriorityType;

    @Column(name = "return_delivery_fee", nullable = false)
    private int returnDeliveryFee;

    @Column(name ="exchange_delivery_fee", nullable = false)
    private int exchangeDeliveryFee;

    @Column(name = "shipping_address_id", nullable = false)
    private long shippingAddressId;

    @Column(name = "return_address_id", nullable = false)
    private long returnAddressId;

    @Column(name = "freeReturnInsuranceYn", nullable = false)
    private boolean freeReturnInsuranceYn;
}
