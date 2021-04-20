package guru.springfamework.springmvcrestv2.bootstrap;

import guru.springfamework.springmvcrestv2.domain.Category;
import guru.springfamework.springmvcrestv2.domain.Customer;
import guru.springfamework.springmvcrestv2.repositories.CategoryRepository;
import guru.springfamework.springmvcrestv2.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
       loadCategories();
       loadCustomers();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        fruits.setName("Dried");

        Category fresh = new Category();
        fruits.setName("Fresh");

        Category exotic = new Category();
        fruits.setName("Exotic");

        Category nuts = new Category();
        fruits.setName("Nuts");

        categoryRepository.saveAll(Arrays.asList(fruits, dried, fresh, exotic, nuts));
        System.out.println("Categories loaded:" + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer jhon = new Customer(1L, "Jhon", "Thomson");
        Customer steve = new Customer(2L, "Steve", "Stevenson");
        Customer joe = new Customer(3L, "Joe", "Satre");

        customerRepository.saveAll(Arrays.asList(jhon, steve, joe));
        System.out.println("Customers loaded: " + customerRepository.count());
    }
}
