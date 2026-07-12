package net.study.ecommerceadminmonolithic.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.study.ecommerceadminmonolithic.domain.customer.Customer;
import net.study.ecommerceadminmonolithic.repository.customer.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    public List<Customer> findAll() {
        return customerRepository.findAll().stream()
                .map(entity -> objectMapper.convertValue(entity, Customer.class))
                .toList();
    }

    public Page<Customer> findAllByActiveCustomer(Pageable pageable) {
        return customerRepository.findByIsDeletedIsFalse(pageable)
                .map(entity -> objectMapper.convertValue(entity, Customer.class));
    }
}
