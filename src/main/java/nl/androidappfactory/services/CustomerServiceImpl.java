package nl.androidappfactory.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import nl.androidappfactory.api.v1.mapper.CustomerMapper;
import nl.androidappfactory.api.v1.model.CustomerDTO;
import nl.androidappfactory.domain.Customer;
import nl.androidappfactory.repositories.CustomerRepository;

@Service
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
				// .map(customer -> customerMapper.customerToCustomerDTO(customer))
				.map(customer -> convertToDTO(customer))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {

		Optional<Customer> customerOptional = customerRepository.findById(id);
		if (!customerOptional.isPresent()) {
			throw new RuntimeException("Customer not found, id: " + id);
		}
		return convertToDTO(customerOptional.get());
	}

	@Override
	public CustomerDTO getCustomerByFirstName(String name) {

		return convertToDTO(customerRepository.findByFirstName(name));

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

	private CustomerDTO saveCustomer(Customer customer) {

		return convertToDTO(customerRepository.save(customer));
	}

	// @Override
	// public CustomerDTO getCustomerByLastName(String name) {
	//
	// return customerMapper.customerToCustomerDTO(customerRepository.findByLastName(name));
	// }

	private CustomerDTO convertToDTO(Customer customer) {
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
		customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
		return customerDTO;
	}

}
