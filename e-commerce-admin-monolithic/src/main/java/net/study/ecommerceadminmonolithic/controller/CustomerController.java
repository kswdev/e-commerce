package net.study.ecommerceadminmonolithic.controller;

import lombok.RequiredArgsConstructor;
import net.study.ecommerceadminmonolithic.controller.dto.CustomerDTO;
import net.study.ecommerceadminmonolithic.service.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers(@PageableDefault() Pageable pageable) {

        return customerService.findAllByActiveCustomer(pageable)
                .stream()
                .map(CustomerDTO::of)
                .toList();
    }
}
