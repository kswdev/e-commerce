package net.study.ecommerceadminmonolithic.repository;

import net.study.ecommerceadminmonolithic.entity.Product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
