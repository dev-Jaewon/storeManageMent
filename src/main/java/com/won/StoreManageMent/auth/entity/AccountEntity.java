package com.won.StoreManageMent.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String platform;

    @Column(name = "platform_id", nullable = false)
    private String platformId;

    @Column(name = "nickname", nullable = false)
    private String nickName;

    @Column(name = "profile_image", nullable = false)
    private String profileImage;
}
