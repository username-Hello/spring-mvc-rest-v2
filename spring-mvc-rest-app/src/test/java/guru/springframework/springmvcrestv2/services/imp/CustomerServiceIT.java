package guru.springframework.springmvcrestv2.services.imp;

import guru.springframework.springmvcrestv2.api.v1.CustomerDTO;
import guru.springframework.springmvcrestv2.api.v1.mapper.CustomerMapper;
import guru.springframework.springmvcrestv2.bootstrap.Bootstrap;
import guru.springframework.springmvcrestv2.domain.Customer;
import guru.springframework.springmvcrestv2.repositories.CategoryRepository;
import guru.springframework.springmvcrestv2.repositories.CustomerRepository;
import guru.springframework.springmvcrestv2.repositories.VendorRepository;
import guru.springframework.springmvcrestv2.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceIT {

    public static final String UPDATED_FIRST_NAME = "Updated_FirstName";
    public static final String UPDATED_LAST_NAME = "Updated_SecondName";

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @Before
    public void setUp() {
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchFirstName() {
        Long id = findId();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO givenCustomer = new CustomerDTO();
        givenCustomer.setFirstName(UPDATED_FIRST_NAME);

        CustomerDTO result = customerService.patchCustomer(id, givenCustomer);
        assertEquals(UPDATED_FIRST_NAME, result.getFirstName());
        assertEquals(originalLastName, result.getLastName());
        assertEquals("/api/v1/customers/" + id, result.getCustomer_url());
    }

    @Test
    public void patchLastName() {
        Long id = findId();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstName = originalCustomer.getFirstName();

        CustomerDTO givenCustomer = new CustomerDTO();
        givenCustomer.setLastName(UPDATED_LAST_NAME);

        CustomerDTO result = customerService.patchCustomer(id, givenCustomer);
        assertEquals(UPDATED_LAST_NAME, result.getLastName());
        assertEquals(originalFirstName, result.getFirstName());
        assertEquals("/api/v1/customers/" + id, result.getCustomer_url());
    }

    private Long findId() {
        return customerRepository.findAll().get(0).getId();
    }
}
