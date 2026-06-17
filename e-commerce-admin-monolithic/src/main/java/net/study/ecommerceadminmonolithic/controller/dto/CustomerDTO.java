package net.study.ecommerceadminmonolithic.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.study.ecommerceadminmonolithic.entity.customer.CustomerEntity;
import net.study.ecommerceadminmonolithic.entity.customer.CustomerGrade;

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

    public static CustomerDTO of(CustomerEntity customerEntity) {
        return new CustomerDTO(
                customerEntity.getCustomerId(),
                customerEntity.getCustomerName(),
                customerEntity.getAge(),
                customerEntity.getPhoneNumber(),
                customerEntity.getAddress(),
                customerEntity.getGrade(),
                customerEntity.isDeleted(),
                customerEntity.getCreatedAt(),
                customerEntity.getUpdatedAt()
        );
    }
}
