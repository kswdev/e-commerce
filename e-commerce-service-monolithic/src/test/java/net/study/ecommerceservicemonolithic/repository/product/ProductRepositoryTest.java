package net.study.ecommerceservicemonolithic.repository.product;

import net.study.ecommerceservicemonolithic.repository.category.CategoryRepository;
import net.study.ecommerceservicemonolithic.repository.category.entity.CategoryEntity;
import net.study.ecommerceservicemonolithic.repository.product.entity.ProductEntity;
import net.study.ecommerceservicemonolithic.repository.vendor.VendorRepository;
import net.study.ecommerceservicemonolithic.repository.vendor.entity.VendorEntity;
import net.study.ecommerceservicemonolithic.repository.vendor.entity.VendorStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private VendorRepository vendorRepository;

    private CategoryEntity category;
    private VendorEntity vendor;

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
        vendorRepository.deleteAll();
        categoryRepository.deleteAll();

        VendorEntity newVendor = new VendorEntity();
        newVendor.setVendorName("Test Vendor");
        newVendor.setVendorStatus(VendorStatus.ENABLED);
        newVendor.setDeleted(false);
        vendor = vendorRepository.save(newVendor);

        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setName("전자기기");
        category = categoryRepository.save(newCategory);
    }

    // -------------------------------------------------
    // Create
    // -------------------------------------------------

    @Test
    @DisplayName("save - 상품을 저장하면 ID가 생성된다")
    void save_assigns_id() {
        // given
        ProductEntity product = product("iphone", BigDecimal.valueOf(1_200_000), false);

        // when
        ProductEntity saved = productRepository.save(product);

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("iphone");
        assertThat(saved.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(1_200_000));
        assertThat(saved.isDeleted()).isFalse();
    }

    // -------------------------------------------------
    // Read
    // -------------------------------------------------

    @Test
    @DisplayName("findById - 저장된 상품을 ID로 조회할 수 있다")
    void findById_returns_saved_product() {
        // given
        ProductEntity saved = productRepository.save(product("macbook", BigDecimal.valueOf(2_500_000), false));

        // when
        Optional<ProductEntity> result = productRepository.findById(saved.getId());

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("macbook");
    }

    @Test
    @DisplayName("findById - 존재하지 않는 ID 조회 시 빈 Optional을 반환한다")
    void findById_returns_empty_for_nonexistent_id() {
        // when
        Optional<ProductEntity> result = productRepository.findById(-1L);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("findAll - 저장된 모든 상품을 조회한다")
    void findAll_returns_all_products() {
        // given
        productRepository.save(product("iphone", BigDecimal.TEN, false));
        productRepository.save(product("ipad", BigDecimal.ONE, false));
        productRepository.save(product("macbook", BigDecimal.valueOf(100), true));

        // when
        List<ProductEntity> result = productRepository.findAll();

        // then
        assertThat(result).hasSize(3);
    }

    @Test
    @DisplayName("findByIsDeletedIsFalse - 삭제되지 않은 상품만 반환한다")
    void findByIsDeletedIsFalse_returns_only_active_products() {
        // given
        productRepository.save(product("iphone", BigDecimal.TEN, false));
        productRepository.save(product("ipad", BigDecimal.ONE, true));
        productRepository.save(product("galaxy", BigDecimal.TEN, false));

        // when
        List<ProductEntity> result = productRepository.findByIsDeletedIsFalse();

        // then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(ProductEntity::getName)
                .containsExactly("iphone", "galaxy");
    }

    // -------------------------------------------------
    // Update
    // -------------------------------------------------

    @Test
    @DisplayName("save - 상품 이름과 가격을 수정하면 변경된 값으로 저장된다")
    void save_updates_product_fields() {
        // given
        ProductEntity saved = productRepository.save(product("iphone 15", BigDecimal.valueOf(1_200_000), false));

        // when
        saved.setName("iphone 16");
        saved.setPrice(BigDecimal.valueOf(1_400_000));
        productRepository.save(saved);

        // then
        ProductEntity updated = productRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getName()).isEqualTo("iphone 16");
        assertThat(updated.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(1_400_000));
    }

    @Test
    @DisplayName("save - isDeleted를 true로 변경하면 논리 삭제 처리된다")
    void save_soft_deletes_product() {
        // given
        ProductEntity saved = productRepository.save(product("airpods", BigDecimal.valueOf(300_000), false));

        // when
        saved.setDeleted(true);
        productRepository.save(saved);

        // then
        List<ProductEntity> activeProducts = productRepository.findByIsDeletedIsFalse();
        assertThat(activeProducts).isEmpty();
    }

    // -------------------------------------------------
    // Delete
    // -------------------------------------------------

    @Test
    @DisplayName("deleteById - 상품을 삭제하면 조회되지 않는다")
    void deleteById_removes_product() {
        // given
        ProductEntity saved = productRepository.save(product("galaxy s24", BigDecimal.valueOf(1_100_000), false));

        // when
        productRepository.deleteById(saved.getId());

        // then
        Optional<ProductEntity> result = productRepository.findById(saved.getId());
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("deleteAll - 전체 삭제 후 조회 결과가 비어 있다")
    void deleteAll_clears_all_products() {
        // given
        productRepository.save(product("product A", BigDecimal.TEN, false));
        productRepository.save(product("product B", BigDecimal.ONE, false));

        // when
        productRepository.deleteAll();

        // then
        assertThat(productRepository.findAll()).isEmpty();
    }

    // -------------------------------------------------
    // Helper
    // -------------------------------------------------

    private ProductEntity product(String name, BigDecimal price, boolean isDeleted) {
        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setPrice(price);
        product.setDeleted(isDeleted);
        product.setCategory(category);
        product.setVendor(vendor);
        return product;
    }
}