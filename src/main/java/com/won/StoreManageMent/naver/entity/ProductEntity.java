package com.won.StoreManageMent.naver.entity;

import com.won.StoreManageMent.auth.entity.AccountEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "detail_text", nullable = true)
    private String detailText;

    @Column(name = "category", nullable = true)
    private String category;

    @Column(name = "tags", nullable = true)
    private List<String > tags;

    @Column(name = "createat", nullable = true)
    private LocalDateTime createat;

    @Column(name = "updateat", nullable = true)
    private LocalDateTime updateat;

    @Column(name = "link_store", nullable = true)
    private String linkStore;

    @Column(name = "link_product", nullable = true)
    private String linkProduct;

    @Column(name = "incom_price", nullable = true)
    private String incomPrice;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "crawling_option_color", nullable = true)
    private String crawlingOptionColor;

    @Column(name = "originproductno", nullable = true)
    private String originProductNo;

    @OneToOne
    @JoinColumn(name = "price_id", nullable = true)
    private PriceEntity price;
}
