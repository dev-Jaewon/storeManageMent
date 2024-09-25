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

    @Column(nullable = false)
    private String platform;

    @Column(name = "platform_id", nullable = false)
    private String platformId;

    @Column(name = "nickname", nullable = false)
    private String nickName;

    @Column(name = "profile_image", nullable = false)
    private String profileImage;
}
