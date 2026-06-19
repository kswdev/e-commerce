package net.study.ecommerceadminmonolithic.repository.product;

import net.study.ecommerceadminmonolithic.repository.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @EntityGraph(attributePaths = "vendor")
    Page<ProductEntity> findAll(Pageable pageable);
}
