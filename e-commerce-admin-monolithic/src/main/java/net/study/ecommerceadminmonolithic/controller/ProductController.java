package net.study.ecommerceadminmonolithic.controller;

import net.study.ecommerceadminmonolithic.controller.dto.ProductDTO;
import net.study.ecommerceadminmonolithic.entity.Product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping
    public Page<ProductDTO> getProducts() {

        return new PageImpl<>(List.of(
                dummyProductOnlyTest("이어폰", BigDecimal.TWO),
                dummyProductOnlyTest("헤드셋", BigDecimal.ONE),
                dummyProductOnlyTest("냉장고", BigDecimal.TEN)));
    }

    private ProductDTO dummyProductOnlyTest(String name, BigDecimal price) {
        ProductDTO dto = new ProductDTO();
        dto.setName(name);
        dto.setPrice(price);
        return dto;
    }
}
