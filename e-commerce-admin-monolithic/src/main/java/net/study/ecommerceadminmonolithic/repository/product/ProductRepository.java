package net.study.ecommerceadminmonolithic.repository.product;

import net.study.ecommerceadminmonolithic.repository.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
