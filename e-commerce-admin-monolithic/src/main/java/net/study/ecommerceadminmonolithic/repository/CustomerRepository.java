package net.study.ecommerceadminmonolithic.repository;

import net.study.ecommerceadminmonolithic.entity.customer.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Page<CustomerEntity> findByIsDeletedIsFalse(Pageable pageable);
}
