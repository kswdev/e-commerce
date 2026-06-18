package net.study.ecommerceadminmonolithic.repository.vendor;

import net.study.ecommerceadminmonolithic.repository.vendor.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
}
