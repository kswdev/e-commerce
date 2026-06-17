package net.study.ecommerceadminmonolithic.controller;

import net.study.ecommerceadminmonolithic.repository.customer.entity.CustomerEntity;
import net.study.ecommerceadminmonolithic.repository.customer.entity.CustomerGrade;
import net.study.ecommerceadminmonolithic.service.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * TDD - Controller Layer
 * HTTP 요청/응답 형식과 Service 위임을 검증한다.
 */
@WebMvcTest(CustomerController.class)
@DisplayName("[Unit] CustomerController")
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    @Test
    @DisplayName("GET /api/v1/customers - 200 OK 와 활성 고객 목록을 반환한다")
    void getCustomers_returns_active_customer_list() throws Exception {
        // Given
        CustomerEntity customer = customerEntity(1L, "Hong Gildong", 30, CustomerGrade.VIP, false);
        Page<CustomerEntity> page = new PageImpl<>(List.of(customer));
        given(customerService.findAllByActiveCustomer(any(Pageable.class))).willReturn(page);

        // When & Then
        mockMvc.perform(get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].customerId").value(1))
                .andExpect(jsonPath("$.content[0].customerName").value("Hong Gildong"))
                .andExpect(jsonPath("$.content[0].age").value(30))
                .andExpect(jsonPath("$.content[0].grade").value("VIP"));
    }

    @Test
    @DisplayName("GET /api/v1/customers - 고객이 없으면 빈 배열을 반환한다")
    void getCustomers_returns_empty_list_when_no_customers() throws Exception {
        // Given
        Page<CustomerEntity> emptyPage = new PageImpl<>(Collections.emptyList());
        given(customerService.findAllByActiveCustomer(any(Pageable.class))).willReturn(emptyPage);

        // When & Then
        mockMvc.perform(get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(0))
                .andExpect(jsonPath("$.totalElements").value(0));
    }

    @Test
    @DisplayName("GET /api/v1/customers - Pageable 파라미터를 Service에 전달한다")
    void getCustomers_delegates_pageable_to_service() throws Exception {
        // Given
        Page<CustomerEntity> emptyPage = new PageImpl<>(Collections.emptyList());
        given(customerService.findAllByActiveCustomer(any(Pageable.class))).willReturn(emptyPage);

        // When
        mockMvc.perform(get("/api/v1/customers")
                        .param("page", "1")
                        .param("size", "5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then
        verify(customerService).findAllByActiveCustomer(any(Pageable.class));
    }

    @Test
    @DisplayName("GET /api/v1/customers - 여러 고객이 있을 때 전체 목록을 순서대로 반환한다")
    void getCustomers_returns_multiple_customers_in_order() throws Exception {
        // Given
        List<CustomerEntity> customers = List.of(
                customerEntity(1L, "Hong Gildong", 30, CustomerGrade.VIP, false),
                customerEntity(2L, "Kim Younghee", 25, CustomerGrade.BASIC, false),
                customerEntity(3L, "Lee Sunshin", 45, CustomerGrade.VIP, false)
        );
        Page<CustomerEntity> page = new PageImpl<>(customers);
        given(customerService.findAllByActiveCustomer(any(Pageable.class))).willReturn(page);

        // When & Then
        mockMvc.perform(get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(3))
                .andExpect(jsonPath("$.content[0].customerName").value("Hong Gildong"))
                .andExpect(jsonPath("$.content[1].customerName").value("Kim Younghee"))
                .andExpect(jsonPath("$.content[2].customerName").value("Lee Sunshin"))
                .andExpect(jsonPath("$.totalElements").value(3));
    }

    @Test
    @DisplayName("GET /api/v1/customers - deleted 필드가 응답에 포함된다")
    void getCustomers_response_includes_deleted_field() throws Exception {
        // Given
        CustomerEntity customer = customerEntity(1L, "Hong Gildong", 30, CustomerGrade.BASIC, false);
        Page<CustomerEntity> page = new PageImpl<>(List.of(customer));
        given(customerService.findAllByActiveCustomer(any(Pageable.class))).willReturn(page);

        // When & Then: isDeleted 필드는 Jackson이 boolean getter 'isDeleted()' 를 'deleted' 키로 직렬화
        mockMvc.perform(get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].deleted").value(false));
    }

    // -------------------------------------------------
    // Helper
    // -------------------------------------------------
    private CustomerEntity customerEntity(Long id, String name, int age,
                                          CustomerGrade grade, boolean isDeleted) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(id);
        entity.setCustomerName(name);
        entity.setAge(age);
        entity.setGrade(grade);
        entity.setDeleted(isDeleted);
        return entity;
    }
}
