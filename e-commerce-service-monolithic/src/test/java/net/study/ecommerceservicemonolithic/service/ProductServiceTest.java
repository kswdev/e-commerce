package net.study.ecommerceservicemonolithic.service;

import net.study.ecommerceservicemonolithic.domain.Product;
import net.study.ecommerceservicemonolithic.repository.category.entity.CategoryEntity;
import net.study.ecommerceservicemonolithic.repository.product.ProductRepository;
import net.study.ecommerceservicemonolithic.repository.product.entity.ProductEntity;
import net.study.ecommerceservicemonolithic.repository.vendor.entity.VendorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("ProductService Test")
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @DisplayName("삭제되지 않은 Product 조회 성공 테스트")
    @Test
    void findByIsDeletedIsFalse_returns_only_active_products() {
        //given
        given(productRepository.findByIsDeletedIsFalse()).willReturn(List.of(
                product("iphone", BigDecimal.valueOf(1_000), false),
                product("ipad", BigDecimal.valueOf(1_000), false),
                product("galaxy", BigDecimal.valueOf(1_000), false)
        ));

        //when
        List<Product> results = productService.getProducts();

        //then
        assertThat(results).hasSize(3);
    }

    @DisplayName("상품 아이디를 통한 상품 조회 테스트")
    @Test
    void findProductById_returns_product_by_id() {
        //given
        Long productId = 1L;
        given(productRepository.findById(productId)).willReturn(Optional.of(product("test", BigDecimal.valueOf(1_000), false)));

        //when
        Product result = productService.getProduct(productId);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("test");
    }

    private ProductEntity product(String name, BigDecimal price, boolean isDeleted) {
        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setPrice(price);
        product.setDeleted(isDeleted);

        VendorEntity vendor = new VendorEntity();
        vendor.setVendorId(1L);
        vendor.setVendorName("test-vendor");
        product.setVendor(vendor);

        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setName("test-category");
        product.setCategory(category);

        return product;
    }
}