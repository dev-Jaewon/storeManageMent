package com.won.StoreManageMent.naver.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detail_attribute")
@Builder
@Getter
@Setter
public class DetailAttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "minor_purchasable")
    private boolean minorPurchasable;

    @OneToOne
    @JoinColumn(name = "product_info_provided_notice_id")
    private ProductInfoProvidedNoticeEntity productInfoProvidedNoticeEntity;

    @Column(nullable = false, name = "after_service_telephone_number")
    private String afterServiceTelephoneNumber;

    @Column(nullable = false, name = "after_service_guide_content")
    private String afterServiceGuideContent;

    @Column(nullable = false, name = "origin_area_code")
    private String originAreaCode;

    @Column(nullable = false, name = "kc_exemption_type")
    private String kcExemptionType;

    @Column(nullable = false, name = "kc_certified_product_exclusion_yn")
    private String kcCertifiedProductExclusionYn;
}
