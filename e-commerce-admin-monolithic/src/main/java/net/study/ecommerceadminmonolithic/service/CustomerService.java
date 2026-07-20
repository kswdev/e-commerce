package net.study.ecommerceadminmonolithic.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.study.ecommerceadminmonolithic.domain.customer.Customer;
import net.study.ecommerceadminmonolithic.repository.customer.CustomerRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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

    public int getDailyCustomerJoinCount() {
        Pair<OffsetDateTime, OffsetDateTime> today = getStartAndEndDateTime();
        return customerRepository.findByCreatedAtBetween(today.a, today.b).size();
    }

    private Pair<OffsetDateTime, OffsetDateTime> getStartAndEndDateTime() {
        OffsetDateTime now = OffsetDateTime.now();
        ZoneOffset offset = now.getOffset();

        OffsetDateTime startOfDay = now.toLocalDate().atStartOfDay().atOffset(offset);
        OffsetDateTime endOfDay = now.toLocalDate().atTime(LocalTime.MAX).atOffset(offset);
        return new Pair<>(startOfDay, endOfDay);
    }
}
