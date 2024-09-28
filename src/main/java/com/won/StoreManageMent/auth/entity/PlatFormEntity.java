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
@Table(name = "platform")
@AllArgsConstructor
@NoArgsConstructor
public class PlatFormEntity {

    @Id
    @GeneratedValue
    @Column(name = "platform_id")
    private Long platformId;

    @Column(nullable = false)
    private String name;
}