package net.study.ecommerceadminmonolithic.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.study.ecommerceadminmonolithic.entity.Customer.CustomerEntity;
import net.study.ecommerceadminmonolithic.repository.CustomerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    public List<CustomerEntity> findAllByActiveCustomer(Pageable pageable) {
        return customerRepository.findByIsDeletedIsFalse(pageable);
    }
}
