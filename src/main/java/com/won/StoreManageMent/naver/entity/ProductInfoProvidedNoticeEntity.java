package com.won.StoreManageMent.naver.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name = "product_info_provided_notice")
public class ProductInfoProvidedNoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "product_info_provided_notice_type")
    private String productInfoProvidedNoticeType;

    @OneToOne
    @JoinColumn(name = "etc_id", unique = true)
    private ProductInfoProvidedNoticeEtcEntity productInfoProvidedNoticeEtc;
}
