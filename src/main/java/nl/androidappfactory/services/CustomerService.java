package nl.androidappfactory.services;

import java.util.List;

import org.springframework.stereotype.Service;

import nl.androidappfactory.api.v1.model.CustomerDTO;

@Service
public interface CustomerService {

	public List<CustomerDTO> getAllCustomers();

	public CustomerDTO getCustomerByFirstName(String name);

}
