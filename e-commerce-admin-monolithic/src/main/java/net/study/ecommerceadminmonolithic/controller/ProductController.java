package net.study.ecommerceadminmonolithic.controller;

import lombok.RequiredArgsConstructor;
import net.study.ecommerceadminmonolithic.controller.dto.ProductDTO;
import net.study.ecommerceadminmonolithic.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> getProducts(@PageableDefault Pageable pageable) {

        return productService.findAll(pageable)
                .map(ProductDTO::of);
    }
}
