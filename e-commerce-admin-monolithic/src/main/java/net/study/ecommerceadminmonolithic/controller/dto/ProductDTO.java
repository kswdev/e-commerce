package net.study.ecommerceadminmonolithic.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter @Setter
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String company;
    private boolean isExposed;
    private BigDecimal remnant;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
