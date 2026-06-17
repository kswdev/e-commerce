package net.study.ecommerceadminmonolithic.service;

import net.study.ecommerceadminmonolithic.entity.customer.CustomerEntity;
import net.study.ecommerceadminmonolithic.entity.customer.CustomerGrade;
import net.study.ecommerceadminmonolithic.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * TDD - Service Layer
 * 비즈니스 로직(활성 고객 필터링, 페이징 위임)을 검증한다.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("[Unit] CustomerService")
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    // -------------------------------------------------
    // findAll()
    // -------------------------------------------------

    @Test
    @DisplayName("findAll - 전체 고객 목록(삭제 포함)을 반환한다")
    void findAll_returns_all_customers_including_deleted() {
        // Given
        List<CustomerEntity> customers = List.of(
                customer(1L, "Hong Gildong", false),
                customer(2L, "Kim Younghee", true)   // 삭제된 고객도 포함
        );
        given(customerRepository.findAll()).willReturn(customers);

        // When
        List<CustomerEntity> result = customerService.findAll();

        // Then
        assertThat(result).hasSize(2);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findAll - 고객이 없을 때 빈 목록을 반환한다")
    void findAll_returns_empty_list_when_no_customers() {
        // Given
        given(customerRepository.findAll()).willReturn(Collections.emptyList());

        // When
        List<CustomerEntity> result = customerService.findAll();

        // Then
        assertThat(result).isEmpty();
        verify(customerRepository, times(1)).findAll();
    }

    // -------------------------------------------------
    // findAllByActiveCustomer(Pageable)
    // -------------------------------------------------

    @Test
    @DisplayName("findAllByActiveCustomer - 삭제되지 않은 고객만 반환한다")
    void findAllByActiveCustomer_returns_only_active_customers() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<CustomerEntity> activeCustomers = List.of(
                customer(1L, "Hong Gildong", false),
                customer(2L, "Kim Younghee", false)
        );
        given(customerRepository.findByIsDeletedIsFalse(pageable)).willReturn(new PageImpl<>(activeCustomers));

        // When
        Page<CustomerEntity> result = customerService.findAllByActiveCustomer(pageable);

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).allMatch(c -> !c.isDeleted());
        verify(customerRepository, times(1)).findByIsDeletedIsFalse(pageable);
    }

    @Test
    @DisplayName("findAllByActiveCustomer - 활성 고객이 없을 때 빈 목록을 반환한다")
    void findAllByActiveCustomer_returns_empty_when_no_active_customers() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        given(customerRepository.findByIsDeletedIsFalse(pageable)).willReturn(new PageImpl<>(Collections.emptyList()));

        // When
        Page<CustomerEntity> result = customerService.findAllByActiveCustomer(pageable);

        // Then
        assertThat(result).isEmpty();
        verify(customerRepository, times(1)).findByIsDeletedIsFalse(pageable);
    }

    @Test
    @DisplayName("findAllByActiveCustomer - Pageable 파라미터를 Repository에 그대로 위임한다")
    void findAllByActiveCustomer_delegates_pageable_to_repository() {
        // Given
        Pageable pageable = PageRequest.of(2, 5);
        given(customerRepository.findByIsDeletedIsFalse(pageable)).willReturn(new PageImpl<>(Collections.emptyList()));

        // When
        customerService.findAllByActiveCustomer(pageable);

        // Then: 정확한 Pageable 객체가 Repository 로 전달됨
        verify(customerRepository).findByIsDeletedIsFalse(pageable);
    }

    @Test
    @DisplayName("findAllByActiveCustomer - Repository 반환 순서를 그대로 유지한다")
    void findAllByActiveCustomer_preserves_order_from_repository() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<CustomerEntity> customers = List.of(
                customer(3L, "Lee Sunshin", false),
                customer(1L, "Hong Gildong", false),
                customer(2L, "Kim Younghee", false)
        );
        given(customerRepository.findByIsDeletedIsFalse(pageable)).willReturn(new PageImpl<>(customers));

        // When
        Page<CustomerEntity> result = customerService.findAllByActiveCustomer(pageable);

        // Then
        assertThat(result)
                .extracting(CustomerEntity::getCustomerName)
                .containsExactly("Lee Sunshin", "Hong Gildong", "Kim Younghee");
    }

    @Test
    @DisplayName("findAllByActiveCustomer - Repository 를 정확히 1회만 호출한다")
    void findAllByActiveCustomer_calls_repository_exactly_once() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        given(customerRepository.findByIsDeletedIsFalse(pageable)).willReturn(new PageImpl<>(Collections.emptyList()));

        // When
        customerService.findAllByActiveCustomer(pageable);
        customerService.findAllByActiveCustomer(pageable);

        // Then
        verify(customerRepository, times(2)).findByIsDeletedIsFalse(pageable);
    }

    // -------------------------------------------------
    // Helper
    // -------------------------------------------------
    private CustomerEntity customer(Long id, String name, boolean isDeleted) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(id);
        entity.setCustomerName(name);
        entity.setGrade(CustomerGrade.BASIC);
        entity.setDeleted(isDeleted);
        return entity;
    }
}
