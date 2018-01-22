package nl.androidappfactory.api.v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.v1.model.CustomerDTO;
import nl.androidappfactory.api.v1.model.CustomerListDTO;
import nl.androidappfactory.services.CustomerService;

@Slf4j
@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public final static String BASE_URL = "/api/v1/customers";

	CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public ResponseEntity<CustomerListDTO> findAllCustomers() {

		CustomerListDTO customers = new CustomerListDTO(customerService.getAllCustomers());
		log.debug("After getAllCustomers: " + customers);

		return new ResponseEntity<CustomerListDTO>(customers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable String id) {

		CustomerDTO customerDTO = customerService.getCustomerById(new Long(id));

		return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {

		log.debug("before create: " + customerDTO);

		CustomerDTO customerCreated = customerService.createCustomer(customerDTO);

		log.debug("after create: " + customerCreated);

		ResponseEntity<CustomerDTO> response = new ResponseEntity<CustomerDTO>(customerCreated, HttpStatus.CREATED);

		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable String id) {

		log.debug("before update: " + customerDTO);

		CustomerDTO customerUpdated = customerService.updateCustomer(new Long(id), customerDTO);

		log.debug("after update: " + customerUpdated);

		ResponseEntity<CustomerDTO> response = new ResponseEntity<CustomerDTO>(customerUpdated, HttpStatus.OK);

		return response;
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CustomerDTO> patchCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable String id) {

		log.debug("before patch: " + customerDTO);

		CustomerDTO customerUpdated = customerService.patchCustomer(new Long(id), customerDTO);

		log.debug("after patch: " + customerUpdated);

		ResponseEntity<CustomerDTO> response = new ResponseEntity<CustomerDTO>(customerUpdated, HttpStatus.OK);

		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> f(@PathVariable Long id) {

		customerService.deleteCustomer(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
