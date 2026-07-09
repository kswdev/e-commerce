package net.study.ecommerceservicemonolithic.repository.vendor;

import net.study.ecommerceservicemonolithic.repository.vendor.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
}
