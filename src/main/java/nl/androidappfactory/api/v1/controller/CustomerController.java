package nl.androidappfactory.api.v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.v1.model.CustomerDTO;
import nl.androidappfactory.api.v1.model.CustomerListDTO;
import nl.androidappfactory.services.CustomerService;

@Slf4j
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public final static String BASE_URL = "/api/v1/customers";

	CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListDTO findAllCustomers() {

		CustomerListDTO customers = new CustomerListDTO(customerService.getAllCustomers());
		log.debug("After getAllCustomers: " + customers);

		return customers;
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	// public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable String id) {
	// line above can be replaceed by next line because this controller is a @RestController
	public CustomerDTO findCustomerById(@PathVariable String id) {

		CustomerDTO customerDTO = customerService.getCustomerById(new Long(id));

		// ResponseEntity<CustomerDTO> response = new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
		// line above can be replaceed by next line because this controller is a @RestController
		return customerDTO;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {

		log.debug("before create: " + customerDTO);

		CustomerDTO customerCreated = customerService.createCustomer(customerDTO);

		log.debug("after create: " + customerCreated);

		return customerCreated;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable String id) {

		log.debug("before update: " + customerDTO);

		CustomerDTO customerUpdated = customerService.updateCustomer(new Long(id), customerDTO);

		log.debug("after update: " + customerUpdated);

		return customerUpdated;
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO patchCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable String id) {

		log.debug("before patch: " + customerDTO);

		CustomerDTO customerUpdated = customerService.patchCustomer(new Long(id), customerDTO);

		log.debug("after patch: " + customerUpdated);

		return customerUpdated;

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id) {

		customerService.deleteCustomer(id);

	}
}
