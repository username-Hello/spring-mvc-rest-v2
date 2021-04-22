package guru.springfamework.springmvcrestv2.repositories;

import guru.springfamework.springmvcrestv2.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
