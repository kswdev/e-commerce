package net.study.ecommerceadminmonolithic.controller;

import net.study.ecommerceadminmonolithic.domain.product.Product;
import net.study.ecommerceadminmonolithic.service.ProductService;
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

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@DisplayName("[Unit] CustomerController")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    @DisplayName("GET /api/v1/products - 200 OK 와 상품 목록을 반환한다.")
    void getProducts_returns_product_list() throws Exception {
        //given
        Product product = product("test-product", BigDecimal.valueOf(10000));
        Page<Product> page = new PageImpl<>(List.of(product));
        given(productService.findAll(any(Pageable.class))).willReturn(page);

        //when & then
        mockMvc.perform(get("/api/v1/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("test-product"))
                .andExpect(jsonPath("$.content[0].price").value(10000));
    }

    private Product product(String name, BigDecimal price) {
        Product domain = new Product();
        domain.setName(name);
        domain.setPrice(price);
        return domain;
    }
}
