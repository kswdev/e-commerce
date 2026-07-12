package net.study.ecommerceadminmonolithic.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.study.ecommerceadminmonolithic.domain.product.Product;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
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

    public static ProductDTO of(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCompany(),
                product.isExposed(),
                product.getStockQuantity(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
