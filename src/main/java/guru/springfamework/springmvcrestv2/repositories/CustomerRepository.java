package guru.springfamework.springmvcrestv2.repositories;

import guru.springfamework.springmvcrestv2.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
