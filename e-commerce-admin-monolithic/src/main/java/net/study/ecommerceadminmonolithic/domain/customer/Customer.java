package net.study.ecommerceadminmonolithic.domain.customer;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Customer {
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private int age;
    private String phoneNumber;
    private String address;
    private CustomerGrade grade;
    private boolean isDeleted;
    private OffsetDateTime createdAt;
    private String createdBy;
    private OffsetDateTime updatedAt;
    private String updatedBy;
}
