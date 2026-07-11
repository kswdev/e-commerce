package net.study.ecommerceservicemonolithic.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private String name;
    private String vendor;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private int stockQuantity;
    private String category;

    @Builder
    public Product(Long id, String name, String vendor, String description, BigDecimal price, String imageUrl, int stockQuantity, String category) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }
}
