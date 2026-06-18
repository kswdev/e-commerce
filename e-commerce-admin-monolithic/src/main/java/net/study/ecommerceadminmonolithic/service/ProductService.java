package net.study.ecommerceadminmonolithic.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.study.ecommerceadminmonolithic.domain.Product;
import net.study.ecommerceadminmonolithic.repository.product.ProductRepository;
import net.study.ecommerceadminmonolithic.repository.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::from);
    }

    private Product from(ProductEntity productEntity) {
        return objectMapper.convertValue(productEntity, Product.class);
    }
}
