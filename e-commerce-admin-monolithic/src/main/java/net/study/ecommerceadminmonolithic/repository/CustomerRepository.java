package net.study.ecommerceadminmonolithic.repository;

import net.study.ecommerceadminmonolithic.entity.Customer.CustomerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findByIsDeletedIsFalse(Pageable pageable);
}
