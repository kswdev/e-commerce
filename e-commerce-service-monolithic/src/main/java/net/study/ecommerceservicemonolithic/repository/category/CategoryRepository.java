package net.study.ecommerceservicemonolithic.repository.category;

import net.study.ecommerceservicemonolithic.repository.category.entity.CategoryEntity;
import net.study.ecommerceservicemonolithic.repository.category.projection.CategoryNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryNameProjection> findAllProjectedBy();
}
