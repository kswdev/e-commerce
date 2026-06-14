package net.study.ecommerceadminmonolithic.repository;

import net.study.ecommerceadminmonolithic.entity.Customer.CustomerEntity;
import net.study.ecommerceadminmonolithic.entity.Customer.CustomerGrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TDD - Repository Layer
 * Note: Spring Boot 4.x dropped @DataJpaTest slice support.
 *       Uses @SpringBootTest + @ActiveProfiles("test") with H2 in-memory DB instead.
 *
 * findByIsDeletedIsFalse 쿼리 메서드의 실제 데이터 접근 동작을 검증한다.
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@DisplayName("[Unit] CustomerRepository")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    // -------------------------------------------------
    // findByIsDeletedIsFalse(Pageable)
    // -------------------------------------------------

    @Test
    @DisplayName("findByIsDeletedIsFalse - 삭제되지 않은 고객만 반환한다")
    void findByIsDeletedIsFalse_returns_only_active_customers() {
        // Given
        customerRepository.save(customer("Hong Gildong", "hong@test.com", false));
        customerRepository.save(customer("Kim Younghee", "kim@test.com", false));
        customerRepository.save(customer("Park Chulsu", "park@test.com", true));  // 삭제됨

        // When
        List<CustomerEntity> result = customerRepository.findByIsDeletedIsFalse(Pageable.unpaged());

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(CustomerEntity::getCustomerName)
                .containsExactlyInAnyOrder("Hong Gildong", "Kim Younghee");
    }

    @Test
    @DisplayName("findByIsDeletedIsFalse - 활성 고객이 없으면 빈 목록을 반환한다")
    void findByIsDeletedIsFalse_returns_empty_when_no_active_customers() {
        // Given
        customerRepository.save(customer("Deleted Customer", "del@test.com", true));

        // When
        List<CustomerEntity> result = customerRepository.findByIsDeletedIsFalse(Pageable.unpaged());

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("findByIsDeletedIsFalse - 데이터가 없으면 빈 목록을 반환한다")
    void findByIsDeletedIsFalse_returns_empty_when_no_data() {
        // When
        List<CustomerEntity> result = customerRepository.findByIsDeletedIsFalse(Pageable.unpaged());

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("findByIsDeletedIsFalse - page size 만큼만 반환한다")
    void findByIsDeletedIsFalse_limits_by_page_size() {
        // Given: 활성 고객 5명
        for (int i = 1; i <= 5; i++) {
            customerRepository.save(customer("Customer" + i, "c" + i + "@test.com", false));
        }

        Pageable pageable = PageRequest.of(0, 3);

        // When
        List<CustomerEntity> result = customerRepository.findByIsDeletedIsFalse(pageable);

        // Then
        assertThat(result).hasSize(3);
    }

    @Test
    @DisplayName("findByIsDeletedIsFalse - 두 번째 페이지를 올바르게 반환한다")
    void findByIsDeletedIsFalse_returns_correct_second_page() {
        // Given: 활성 고객 5명
        for (int i = 1; i <= 5; i++) {
            customerRepository.save(customer("Customer" + i, "c" + i + "@test.com", false));
        }

        // When
        List<CustomerEntity> page1 = customerRepository.findByIsDeletedIsFalse(PageRequest.of(0, 3));
        List<CustomerEntity> page2 = customerRepository.findByIsDeletedIsFalse(PageRequest.of(1, 3));

        // Then
        assertThat(page1).hasSize(3);
        assertThat(page2).hasSize(2);
    }

    @Test
    @DisplayName("findByIsDeletedIsFalse - 모든 고객이 활성이면 전원 반환한다")
    void findByIsDeletedIsFalse_returns_all_when_all_active() {
        // Given
        customerRepository.save(customer("Hong Gildong", "hong@test.com", false));
        customerRepository.save(customer("Kim Younghee", "kim@test.com", false));

        // When
        List<CustomerEntity> result = customerRepository.findByIsDeletedIsFalse(Pageable.unpaged());

        // Then
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("findByIsDeletedIsFalse - 고객 등급(GRADE) 정보가 올바르게 조회된다")
    void findByIsDeletedIsFalse_retrieves_correct_grade() {
        // Given
        CustomerEntity vipCustomer = customer("Hong Gildong", "hong@test.com", false);
        vipCustomer.setGrade(CustomerGrade.VIP);

        CustomerEntity basicCustomer = customer("Kim Younghee", "kim@test.com", false);
        basicCustomer.setGrade(CustomerGrade.BASIC);

        customerRepository.save(vipCustomer);
        customerRepository.save(basicCustomer);

        // When
        List<CustomerEntity> result = customerRepository.findByIsDeletedIsFalse(Pageable.unpaged());

        // Then
        assertThat(result).hasSize(2);
        assertThat(result)
                .extracting(CustomerEntity::getGrade)
                .containsExactlyInAnyOrder(CustomerGrade.VIP, CustomerGrade.BASIC);
    }

    @Test
    @DisplayName("findByIsDeletedIsFalse - 삭제 여부가 섞여 있을 때 올바르게 필터링한다")
    void findByIsDeletedIsFalse_filters_correctly_with_mixed_data() {
        // Given: 활성 3명, 삭제 2명
        customerRepository.save(customer("Active1", "a1@test.com", false));
        customerRepository.save(customer("Active2", "a2@test.com", false));
        customerRepository.save(customer("Active3", "a3@test.com", false));
        customerRepository.save(customer("Deleted1", "d1@test.com", true));
        customerRepository.save(customer("Deleted2", "d2@test.com", true));

        // When
        List<CustomerEntity> result = customerRepository.findByIsDeletedIsFalse(Pageable.unpaged());

        // Then
        assertThat(result).hasSize(3);
        assertThat(result).allMatch(c -> !c.isDeleted());
    }

    // -------------------------------------------------
    // Helper
    // -------------------------------------------------
    private CustomerEntity customer(String name, String email, boolean isDeleted) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerName(name);
        entity.setCustomerEmail(email);
        entity.setGrade(CustomerGrade.BASIC);
        entity.setAge(20);
        entity.setDeleted(isDeleted);
        return entity;
    }
}
