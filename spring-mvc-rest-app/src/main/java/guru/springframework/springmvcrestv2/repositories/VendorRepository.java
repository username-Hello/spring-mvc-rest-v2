package guru.springframework.springmvcrestv2.repositories;

import guru.springframework.springmvcrestv2.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
