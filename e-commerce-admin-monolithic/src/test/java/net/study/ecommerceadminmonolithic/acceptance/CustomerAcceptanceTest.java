package net.study.ecommerceadminmonolithic.acceptance;

import net.study.ecommerceadminmonolithic.entity.Customer.CustomerEntity;
import net.study.ecommerceadminmonolithic.entity.Customer.CustomerGrade;
import net.study.ecommerceadminmonolithic.repository.CustomerRepository;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Acceptance TDD: 사용자 관점에서 Customer API 동작을 검증한다.
 *
 * 인수 조건:
 *   - 어드민은 활성 고객 목록을 페이지 단위로 조회할 수 있다.
 *   - 소프트 삭제(isDeleted=true)된 고객은 목록에 포함되지 않는다.
 *   - 고객이 없을 경우 빈 배열을 반환한다.
 *   - 페이지 크기(size) 파라미터로 반환 수를 제한할 수 있다.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@DisplayName("[Acceptance] Customer API")
class CustomerAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    // -------------------------------------------------
    // Scenario 1: 활성 고객이 존재하면 해당 고객만 반환한다
    // -------------------------------------------------
    @Test
    @DisplayName("활성 고객 목록 조회 - 삭제되지 않은 고객만 반환한다")
    void 활성_고객_목록_조회() throws Exception {
        // Given: 활성 고객 2명, 삭제된 고객 1명
        customerRepository.saveAll(List.of(
                activeCustomer("홍길동", "hong@test.com", CustomerGrade.VIP),
                activeCustomer("김영희", "kim@test.com", CustomerGrade.BASIC),
                deletedCustomer("박철수", "park@test.com")
        ));

        // When & Then
        mockMvc.perform(get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    // -------------------------------------------------
    // Scenario 2: 고객이 없으면 빈 배열을 반환한다
    // -------------------------------------------------
    @Test
    @DisplayName("고객이 없을 때 빈 배열 반환")
    void 고객_없음_빈_배열_반환() throws Exception {
        // Given: 고객 없음

        // When & Then
        mockMvc.perform(get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // -------------------------------------------------
    // Scenario 3: 삭제된 고객만 있으면 빈 배열을 반환한다
    // -------------------------------------------------
    @Test
    @DisplayName("삭제된 고객만 존재할 때 빈 배열 반환")
    void 삭제된_고객만_존재할_때_빈_배열_반환() throws Exception {
        // Given: 삭제된 고객만
        customerRepository.saveAll(List.of(
                deletedCustomer("박철수", "park@test.com"),
                deletedCustomer("이순신", "lee@test.com")
        ));

        // When & Then
        mockMvc.perform(get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // -------------------------------------------------
    // Scenario 4: 페이지네이션 - size 파라미터로 반환 수 제한
    // -------------------------------------------------
    @Test
    @DisplayName("페이지네이션 - size=2 요청 시 2명만 반환한다")
    void 페이지네이션_size_제한() throws Exception {
        // Given: 활성 고객 5명
        customerRepository.saveAll(List.of(
                activeCustomer("고객1", "c1@test.com", CustomerGrade.BASIC),
                activeCustomer("고객2", "c2@test.com", CustomerGrade.BASIC),
                activeCustomer("고객3", "c3@test.com", CustomerGrade.BASIC),
                activeCustomer("고객4", "c4@test.com", CustomerGrade.BASIC),
                activeCustomer("고객5", "c5@test.com", CustomerGrade.VIP)
        ));

        // When & Then: size=2 로 요청
        mockMvc.perform(get("/api/v1/customers")
                        .param("page", "0")
                        .param("size", "2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    // -------------------------------------------------
    // Scenario 5: 응답 JSON 필수 필드 검증
    // -------------------------------------------------
    @Test
    @DisplayName("응답 JSON에 필수 필드가 포함된다")
    void 응답_필수_필드_포함() throws Exception {
        // Given
        CustomerEntity customer = activeCustomer("홍길동", "hong@test.com", CustomerGrade.VIP);
        customer.setAge(30);
        customer.setPhoneNumber("010-1234-5678");
        customer.setAddress("서울시 강남구");
        customerRepository.save(customer);

        // When & Then
        mockMvc.perform(get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerId").exists())
                .andExpect(jsonPath("$[0].customerName").value("홍길동"))
                .andExpect(jsonPath("$[0].age").value(30))
                .andExpect(jsonPath("$[0].phoneNumber").value("010-1234-5678"))
                .andExpect(jsonPath("$[0].address").value("서울시 강남구"))
                .andExpect(jsonPath("$[0].grade").value("VIP"));
    }

    // -------------------------------------------------
    // Helper
    // -------------------------------------------------
    private CustomerEntity activeCustomer(String name, String email, CustomerGrade grade) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerName(name);
        entity.setCustomerEmail(email);
        entity.setGrade(grade);
        entity.setDeleted(false);
        return entity;
    }

    private CustomerEntity deletedCustomer(String name, String email) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerName(name);
        entity.setCustomerEmail(email);
        entity.setGrade(CustomerGrade.BASIC);
        entity.setDeleted(true);
        return entity;
    }
}
