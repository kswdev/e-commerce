package net.study.ecommerceadminmonolithic.acceptance;

import net.study.ecommerceadminmonolithic.repository.product.entity.ProductEntity;
import net.study.ecommerceadminmonolithic.repository.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Acceptance TDD: 사용자 관점에서 Product API 동작을 검증한다.
 *
 * 인수 조건:
 *   - 어드민은 활성 상품 목록을 페이지 단위로 조회할 수 있다.
 *   - 소프트 삭제(isDeleted=true)된 상품은 목록에 포함되지 않는다.
 *   - 노출되지 않은(isExposed=false)된 상품은 목록에 포함되지 않는다.
 *   - 상품이 없을 경우 빈 배열을 반환한다.
 *   - 페이지 크기(size) 파라미터로 반환 수를 제한할 수 있다.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@DisplayName("[Acceptance] Product API")
class ProductAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    // -------------------------------------------------
    // Scenario 1: 전체 상품 조회
    // -------------------------------------------------
    @Test
    @DisplayName("활성 상품 목록 조회 - 삭제되지 않고 활성화된 상품만 반환한다.")
    void 활성_상품_목록_조회() throws Exception {
        //given
        productRepository.saveAll(List.of(
                product("이어폰", BigDecimal.TWO),
                product("헤드셋", BigDecimal.ONE),
                product("냉장고", BigDecimal.TEN)
        ));

        //when & then
        mockMvc.perform(get("/api/v1/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(3))
                .andExpect(jsonPath("$.totalElements").value(3));
    }

    private ProductEntity product(String name, BigDecimal price) {
        ProductEntity entity = new ProductEntity();
        entity.setName(name);
        entity.setPrice(price);
        return entity;
    }
}
