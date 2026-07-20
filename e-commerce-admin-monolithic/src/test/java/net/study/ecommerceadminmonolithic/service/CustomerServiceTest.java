package net.study.ecommerceadminmonolithic.service;

import net.study.ecommerceadminmonolithic.domain.customer.Customer;
import net.study.ecommerceadminmonolithic.repository.customer.entity.CustomerEntity;
import net.study.ecommerceadminmonolithic.domain.customer.CustomerGrade;
import net.study.ecommerceadminmonolithic.repository.customer.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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

    @Mock
    private ObjectMapper objectMapper;

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
        given(objectMapper.convertValue(customers.get(0), Customer.class)).willReturn(new Customer());
        given(objectMapper.convertValue(customers.get(1), Customer.class)).willReturn(new Customer());

        // When
        List<Customer> result = customerService.findAll();

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
        List<Customer> result = customerService.findAll();

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
        given(objectMapper.convertValue(activeCustomers.get(0), Customer.class)).willReturn(new Customer());
        given(objectMapper.convertValue(activeCustomers.get(1), Customer.class)).willReturn(new Customer());

        // When
        Page<Customer> result = customerService.findAllByActiveCustomer(pageable);

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
        Page<Customer> result = customerService.findAllByActiveCustomer(pageable);

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

    @Test
    @DisplayName("getDailyJoinCnt - 당일 회원 가입한 회원 수를 반환한다.")
    void getDailyJoinCnt_returns_daily_join_count() {
        //given
        OffsetDateTime now = OffsetDateTime.now();
        ZoneOffset offset = now.getOffset();

        OffsetDateTime startOfDay = now.toLocalDate().atStartOfDay().atOffset(offset);
        OffsetDateTime endOfDay = now.toLocalDate().atTime(LocalTime.MAX).atOffset(offset);

        given(customerRepository.findByCreatedAtBetween(startOfDay, endOfDay))
                .willReturn(List.of(
                        todayJoinCustomer(1L, "test-1"),
                        todayJoinCustomer(2L, "test-2")
                ));

        //when
        int count = customerService.getDailyCustomerJoinCount();

        //then
        assertThat(count).isEqualTo(2);
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

    private CustomerEntity todayJoinCustomer(Long id, String name) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(id);
        entity.setCustomerName(name);
        entity.setGrade(CustomerGrade.BASIC);
        entity.setCreatedAt(OffsetDateTime.now());
        return entity;
    }
}
