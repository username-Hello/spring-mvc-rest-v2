package guru.springfamework.springmvcrestv2.controllers.v1;

import guru.springfamework.springmvcrestv2.api.v1.CustomerDTO;
import guru.springfamework.springmvcrestv2.api.v1.CustomerListDTO;
import guru.springfamework.springmvcrestv2.domain.Customer;
import guru.springfamework.springmvcrestv2.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    public final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerListDTO> getListOfCustomers() {
        return new ResponseEntity<>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
        return new ResponseEntity<>(
                customerService.getCustomerById(Long.parseLong(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }
}
