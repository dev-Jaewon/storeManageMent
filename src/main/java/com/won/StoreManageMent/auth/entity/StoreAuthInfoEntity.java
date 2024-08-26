package com.won.StoreManageMent.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "store_auth_info")
@AllArgsConstructor
@Builder
public class StoreAuthInfoEntity {
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private PlatFormEntity platformId;

    @Column(nullable = false)
    private String option1;

    @Column(nullable = true)
    private String option2;

}
