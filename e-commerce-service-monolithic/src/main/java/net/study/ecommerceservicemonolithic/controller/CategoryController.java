package net.study.ecommerceservicemonolithic.controller;

import lombok.RequiredArgsConstructor;
import net.study.ecommerceservicemonolithic.repository.category.projection.CategoryNameProjection;
import net.study.ecommerceservicemonolithic.repository.category.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<String> getCategoryNames() {
        return categoryRepository.findAllProjectedBy()
                .stream()
                .map(CategoryNameProjection::getName)
                .toList();
    }
}