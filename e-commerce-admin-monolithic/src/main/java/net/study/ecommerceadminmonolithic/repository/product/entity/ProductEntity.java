package net.study.ecommerceadminmonolithic.repository.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter @Setter
@Entity
@Table(name = "products", schema = "ecommerce_monolithic")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "vendor_id")
    private Long vendorId;
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "is_exposed")
    private boolean isExposed;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;
}

