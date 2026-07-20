package net.study.ecommerceadminmonolithic.controller;

import lombok.RequiredArgsConstructor;
import net.study.ecommerceadminmonolithic.controller.dto.CustomerDTO;
import net.study.ecommerceadminmonolithic.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Page<CustomerDTO> getAllCustomers(@PageableDefault(size = 10) Pageable pageable) {
        return customerService.findAllByActiveCustomer(pageable)
                .map(CustomerDTO::of);
    }

    @GetMapping("/daily-join-cnt")
    public int getDailyCustomerJoinCount() {
        return customerService.getDailyCustomerJoinCount();
    }

    @GetMapping("/daily-quit-cnt")
    public int getDailyCustomerQuitCount() {
        return customerService.getDailyCustomerQuitCount();
    }
}
