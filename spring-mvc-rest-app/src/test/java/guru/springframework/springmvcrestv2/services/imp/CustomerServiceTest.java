package guru.springframework.springmvcrestv2.services.imp;

import guru.springframework.springmvcrestv2.api.v1.CustomerDTO;
import guru.springframework.springmvcrestv2.api.v1.mapper.CustomerMapper;
import guru.springframework.springmvcrestv2.domain.Customer;
import guru.springframework.springmvcrestv2.repositories.CustomerRepository;
import guru.springframework.springmvcrestv2.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static guru.springframework.springmvcrestv2.constants.CoreConstants.BASE_CUSTOMER_URL;

public class CustomerServiceTest {

    public static final String FIRST_NAME = "Test_FirstName";
    public static final String LAST_NAME = "Test_LastName";
    public static final Long ID = 1L;
    public static final String CUSTOMER_URL = BASE_CUSTOMER_URL + "/" + ID;
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

    @Test
    public void updateCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer customer = new Customer(ID, FIRST_NAME, LAST_NAME);
        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDTO result = customerService.updateCustomer(ID, customerDTO);
        String[] resultCustomerUrl = result.getCustomer_url().split("/");
        Long resultId = Long.parseLong(resultCustomerUrl[resultCustomerUrl.length - 1]);
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(CUSTOMER_URL, result.getCustomer_url());
        assertEquals(ID, resultId);
    }

    @Test
    public void deleteCustomer() {
        customerService.deleteCustomer(ID);
        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}