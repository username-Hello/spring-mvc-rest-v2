package guru.springfamework.springmvcrestv2.api.v1.mapper;

import guru.springfamework.springmvcrestv2.api.v1.CustomerDTO;
import guru.springfamework.springmvcrestv2.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer source);

    Customer customerDTOToCustomer(CustomerDTO source);
}
