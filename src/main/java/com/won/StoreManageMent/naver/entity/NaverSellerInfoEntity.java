package com.won.StoreManageMent.naver.entity;

import com.won.StoreManageMent.auth.entity.AccountEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "naver_seller_info")
@Builder
@Getter
@Setter
public class NaverSellerInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @OneToOne
    @JoinColumn(name = "delivery_info_id", nullable = false)
    private DeliveryInfoEntity deliveryInfo;

    @OneToOne
    @JoinColumn(name = "detail_attribute_id", nullable = false)
    private DetailAttributeEntity detailAttribute;
}
