package com.won.StoreManageMent.naver.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "product_info_provided_notice_etc")
public class ProductInfoProvidedNoticeEtcEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "return_cost_reason", nullable = false)
    private String returnCostReason;

    @Column(name = "no_refund_reason", nullable = false)
    private String noRefundReason;

    @Column(name = "quality_assurance_standard", nullable = false)
    private String qualityAssuranceStandard;

    @Column(name = "compensation_procedure", nullable = false)
    private String compensationProcedure;

    @Column(name = "trouble_shooting_contents", nullable = false)
    private String troubleShootingContents;

    @Column(name = "certificateDetails", nullable = false)
    private String certificateDetails;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "customer_service_phoneNumber", nullable = false)
    private String customerServicePhoneNumber;

}
