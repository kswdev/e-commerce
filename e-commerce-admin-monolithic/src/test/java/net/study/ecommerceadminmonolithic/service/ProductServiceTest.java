package net.study.ecommerceadminmonolithic.service;

import net.study.ecommerceadminmonolithic.domain.Product;
import net.study.ecommerceadminmonolithic.repository.product.ProductRepository;
import net.study.ecommerceadminmonolithic.repository.product.entity.ProductEntity;
import net.study.ecommerceadminmonolithic.repository.vendor.VendorRepository;
import net.study.ecommerceadminmonolithic.repository.vendor.entity.VendorEntity;
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
        VendorEntity apple = vendorEntity(1L, "Apple");
        VendorEntity galaxy = vendorEntity(2L, "galaxy");

        List<ProductEntity> products = List.of(
                productEntity(1L, "iphone", 1000L, apple, true),
                productEntity(1L, "ipad", 2000L, apple, false),
                productEntity(1L, "galaxy", 3000L, galaxy, false),
                productEntity(1L, "macbook", 4000L, apple, false)
        );
        given(productRepository.findAll(pageable)).willReturn(new PageImpl<>(products));

        //when
        Page<Product> result = productService.findAll(pageable);

        //then
        assertThat(result).hasSize(4);
        assertThat(result.getContent().getFirst().getId()).isEqualTo(1L);
        assertThat(result.getContent().getFirst().getCompany()).isEqualTo("Apple");
        assertThat(result.getContent().getFirst().getName()).isEqualTo("iphone");
        verify(productRepository, times(1)).findAll(pageable);
    }

    private ProductEntity productEntity(Long id, String name, Long price, VendorEntity vendorEntity, boolean isDeleted) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productEntity.setName(name);
        productEntity.setPrice(BigDecimal.valueOf(price));
        productEntity.setVendor(vendorEntity);
        productEntity.setDeleted(isDeleted);
        return productEntity;
    }

    private VendorEntity vendorEntity(Long id, String name) {
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setVendorId(id);
        vendorEntity.setVendorName(name);
        return vendorEntity;
    }
}