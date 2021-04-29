package guru.springframework.springmvcrestv2.api.v1.mapper;

import guru.springframework.springmvcrestv2.api.v1.CustomerDTO;
import guru.springframework.springmvcrestv2.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer source);

    Customer customerDTOToCustomer(CustomerDTO source);
}
