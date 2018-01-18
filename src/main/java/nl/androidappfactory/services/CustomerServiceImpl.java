package nl.androidappfactory.services;

import java.util.List;
import java.util.stream.Collectors;

import nl.androidappfactory.api.v1.mapper.CustomerMapper;
import nl.androidappfactory.api.v1.model.CustomerDTO;
import nl.androidappfactory.repositories.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	private CustomerMapper customerMapper;

	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {

		return customerRepository.findAll()
				.stream()
				.map(customerMapper::customerToCustomerDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerByFirstName(String name) {

		return customerMapper.customerToCustomerDTO(customerRepository.findByFirstName(name));

	}

	// @Override
	// public CustomerDTO getCustomerByLastName(String name) {
	//
	// return customerMapper.customerToCustomerDTO(customerRepository.findByLastName(name));
	// }

}
