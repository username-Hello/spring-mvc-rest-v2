package guru.springfamework.springmvcrestv2.services;

import guru.springfamework.springmvcrestv2.api.v1.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);
}
