package guru.springfamework.springmvcrestv2.services.imp;

import guru.springfamework.springmvcrestv2.api.v1.CustomerDTO;
import guru.springfamework.springmvcrestv2.api.v1.mapper.CustomerMapper;
import guru.springfamework.springmvcrestv2.domain.Customer;
import guru.springfamework.springmvcrestv2.repositories.CustomerRepository;
import guru.springfamework.springmvcrestv2.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    public static final String FIRST_NAME = "Test_FirstName";
    public static final String LAST_NAME = "Test_LastName";
    public static final Long ID = 1L;
    public static final String CUSTOMER_URL = "/api/v1/customers/" + ID;
    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOs = customerService.getAllCustomers();
        assertEquals(customerDTOs.size(), customers.size());
    }

    @Test
    public void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }

    @Test
    public void createNewCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer customer = new Customer(ID, FIRST_NAME, LAST_NAME);
        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDTO result = customerService.createCustomer(customerDTO);
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(CUSTOMER_URL, result.getCustomer_url());
    }
}