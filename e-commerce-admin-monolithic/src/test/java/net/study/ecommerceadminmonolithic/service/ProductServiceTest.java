package net.study.ecommerceadminmonolithic.service;

import net.study.ecommerceadminmonolithic.domain.Product;
import net.study.ecommerceadminmonolithic.repository.product.ProductRepository;
import net.study.ecommerceadminmonolithic.repository.product.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * TDD - Service Layer
 * 비즈니스 로직(상품 조회, 페이징 위임)을 검증한다.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("[Unit] ProductService")
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(
                productRepository,
                new ObjectMapper()
        );
    }

    @Test
    @DisplayName("findAll - 전체 상품 목록(삭제 포함)을 반환한다.")
    void findAll_returns_all_products_including_deleted() {
        //given
        Pageable pageable = PageRequest.of(0, 10);
        List<ProductEntity> products = List.of(
                product(1L, "iphone", 1000L, true),
                product(1L, "ipad", 2000L, false),
                product(1L, "galaxy", 3000L, false),
                product(1L, "macbook", 4000L, false)
        );
        given(productRepository.findAll(pageable)).willReturn(new PageImpl<>(products));

        //when
        Page<Product> result = productService.findAll(pageable);

        //then
        assertThat(result).hasSize(4);
        assertThat(result.getContent().getFirst().getId()).isEqualTo(1L);
        assertThat(result.getContent().getFirst().getName()).isEqualTo("iphone");
        verify(productRepository, times(1)).findAll(pageable);
    }

    private ProductEntity product(Long id, String name, Long price, boolean isDeleted) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productEntity.setName(name);
        productEntity.setPrice(BigDecimal.valueOf(price));
        productEntity.setDeleted(isDeleted);
        return productEntity;
    }
}