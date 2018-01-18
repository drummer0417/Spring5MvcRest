package nl.androidappfactory.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import nl.androidappfactory.api.v1.model.CustomerDTO;
import nl.androidappfactory.domain.Customer;

@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	@Mapping(source = "id", target = "id") // Mapping is only needed if fieldnames are different
	@Mapping(source = "firstName", target = "firstName") // Mapping is only needed if fieldnames are different
	@Mapping(source = "lastName", target = "lastName") // Mapping is only needed if fieldnames are different
	CustomerDTO customerToCustomerDTO(Customer customer);

	Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
