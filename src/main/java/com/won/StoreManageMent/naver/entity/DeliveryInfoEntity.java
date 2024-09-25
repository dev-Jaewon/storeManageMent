package com.won.StoreManageMent.naver.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "delivery_info")
public class DeliveryInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "delivery_type", nullable = false)
    private String deliveryType;

    @Column(name = "delivery_attribute_type", nullable = false)
    private String deliveryAttributeType;

    @Column(name = "delivery_company", nullable = false)
    private String deliveryCompany;

    @Column(name = "delivery_bundle_group_usable", nullable = false)
    private boolean deliveryBundleGroupUsable;

    @Column(name = "delivery_fee", nullable = false)
    private String deliveryFee;

    @OneToOne
    @JoinColumn(unique = true, name = "claim_delivery_info_id")
    private ClaimDeliveryInfoEntity claimDeliveryInfo;

    @Column(name = "installation_fee", nullable = false)
    private boolean installationFee;
}
