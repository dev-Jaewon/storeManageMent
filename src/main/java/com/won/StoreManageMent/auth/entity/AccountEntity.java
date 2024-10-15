package com.won.StoreManageMent.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naver_id", nullable = true)
    private String naverId;

    @Column(name = "nickname", nullable = false)
    private String nickName;

    @Column(name = "profile_image", nullable = true)
    private String profileImage;

    @Column(name = "account_id", nullable = true)
    private String accountId;

    @Column(name = "password", nullable = true)
    private String password;
}
