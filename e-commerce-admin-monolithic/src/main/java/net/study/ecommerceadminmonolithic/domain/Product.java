package net.study.ecommerceadminmonolithic.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter @Setter
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private String company;
    private String imageUrl;
    private BigDecimal stockQuantity;
    private boolean isExposed;
    private boolean isDeleted;
    private OffsetDateTime createdAt;
    private String createdBy;
    private OffsetDateTime updatedAt;
    private String updatedBy;
}
