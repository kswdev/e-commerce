package net.study.ecommerceservicemonolithic.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.study.ecommerceservicemonolithic.domain.Product;
import net.study.ecommerceservicemonolithic.repository.product.ProductRepository;
import net.study.ecommerceservicemonolithic.repository.product.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findByIsDeletedIsFalse().stream()
                .map(this::from)
                .toList();
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .map(this::from)
                .orElseThrow();
    }

    private Product from(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .imageUrl(productEntity.getImageUrl())
                .category(productEntity.getCategory().getName())
                .vendor(productEntity.getVendor().getVendorName())
                .stockQuantity(productEntity.getStockQuantity())
                .build();
    }
}
