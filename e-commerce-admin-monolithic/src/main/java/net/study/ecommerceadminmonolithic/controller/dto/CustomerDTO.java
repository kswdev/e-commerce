package net.study.ecommerceadminmonolithic.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.study.ecommerceadminmonolithic.domain.customer.Customer;
import net.study.ecommerceadminmonolithic.domain.customer.CustomerGrade;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class CustomerDTO {

    private Long customerId;
    private String customerName;
    private int age;
    private String phoneNumber;
    private String address;
    private CustomerGrade grade;
    private boolean isDeleted;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public static CustomerDTO of(Customer customer) {
        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getAge(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getGrade(),
                customer.isDeleted(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}
