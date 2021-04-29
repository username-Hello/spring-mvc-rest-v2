package guru.springframework.springmvcrestv2.services.imp;

import guru.springframework.springmvcrestv2.api.v1.CustomerDTO;
import guru.springframework.springmvcrestv2.api.v1.mapper.CustomerMapper;
import guru.springframework.springmvcrestv2.domain.Customer;
import guru.springframework.springmvcrestv2.exception.ResourceNotFoundException;
import guru.springframework.springmvcrestv2.repositories.CustomerRepository;
import guru.springframework.springmvcrestv2.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static guru.springframework.springmvcrestv2.constants.CoreConstants.BASE_CUSTOMER_URL;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomer_url(BASE_CUSTOMER_URL + "/"  + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    customerDTO.setCustomer_url(BASE_CUSTOMER_URL + "/" + id);
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
       return saveCustomer(customerMapper.customerDTOToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        return saveCustomer(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(originalCustomer -> {
            if (customerDTO.getFirstName() != null) {
                originalCustomer.setFirstName(customerDTO.getFirstName());
            }
            if (customerDTO.getLastName() != null) {
                originalCustomer.setLastName(customerDTO.getLastName());
            }
            return saveCustomer(originalCustomer);
        }).orElseThrow(ResourceNotFoundException::new);

    }

    private CustomerDTO saveCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        savedCustomerDTO.setCustomer_url(BASE_CUSTOMER_URL + "/" + savedCustomer.getId());
        return savedCustomerDTO;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
