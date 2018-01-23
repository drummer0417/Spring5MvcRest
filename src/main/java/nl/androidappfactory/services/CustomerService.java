package nl.androidappfactory.services;

import java.util.List;

import nl.androidappfactory.api.v1.model.CustomerDTO;

public interface CustomerService {

	public List<CustomerDTO> getAllCustomers();

	public CustomerDTO getCustomerById(Long id);

	public CustomerDTO getCustomerByFirstName(String name);

	public CustomerDTO createCustomer(CustomerDTO customerDTO);

	public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

	public void deleteCustomer(Long id);
}
