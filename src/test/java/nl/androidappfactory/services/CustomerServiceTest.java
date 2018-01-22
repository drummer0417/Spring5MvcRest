package nl.androidappfactory.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.androidappfactory.api.v1.mapper.CustomerMapper;
import nl.androidappfactory.api.v1.model.CustomerDTO;
import nl.androidappfactory.domain.Customer;
import nl.androidappfactory.repositories.CustomerRepository;

public class CustomerServiceTest {

	private static final Long ID1 = 1l;
	private static final Long ID2 = 2l;
	private static final String FIRST_NAME1 = "FN1";
	private static final String FIRST_NAME2 = "FN2";
	private static final String LAST_NAME1 = "LN1";
	private static final String LAST_NAME2 = "LN2";
	private static final String URL1 = "/api/v1/customers/1";

	CustomerMapper customerMapper = CustomerMapper.INSTANCE;;

	CustomerServiceImpl customerService;

	@Mock
	CustomerRepository customerRepository;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		customerService = new CustomerServiceImpl(customerRepository, customerMapper);
	}

	@Test
	public void getAllCustomers() {

		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(ID1, FIRST_NAME1, LAST_NAME1));
		customers.add(new Customer(ID2, FIRST_NAME2, LAST_NAME2));

		when(customerRepository.findAll()).thenReturn(customers);

		List<CustomerDTO> customerDTOs = customerService.getAllCustomers();

		assertEquals(2, customerDTOs.size());
		assertEquals(FIRST_NAME1, customerDTOs.get(0).getFirstName());

	}

	@Test
	public void getCustomerById() {

		Customer customer = new Customer(ID1, FIRST_NAME1, LAST_NAME1);

		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

		CustomerDTO customerDTO = customerService.getCustomerById(new Long(ID1));

		assertEquals(FIRST_NAME1, customerDTO.getFirstName());
		assertEquals(LAST_NAME1, customerDTO.getLastName());
		assertEquals(URL1, customerDTO.getCustomerUrl());

	}

	@Test(expected = RuntimeException.class)
	public void getCustomerByIdNotFound() {

		when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

		CustomerDTO customerDTO = customerService.getCustomerById(new Long(ID1));

		assertEquals("/api/v1/customers/" + ID1, customerDTO.getCustomerUrl());
		assertEquals(FIRST_NAME1, customerDTO.getFirstName());
		assertEquals(LAST_NAME1, customerDTO.getLastName());
	}

	@Test
	public void testCreateCustomer() {

		Customer savedCustomer = new Customer(ID1, FIRST_NAME1, LAST_NAME1);

		when(customerRepository.save(any())).thenReturn(savedCustomer);

		CustomerDTO customerDTO = customerService.createCustomer(new CustomerDTO());

		assertEquals(URL1, customerDTO.getCustomerUrl());
		assertEquals(FIRST_NAME1, customerDTO.getFirstName());
		assertEquals(LAST_NAME1, customerDTO.getLastName());
		assertEquals(URL1, customerDTO.getCustomerUrl());

	}

	@Test
	public void testUpdateCustomer() {

		CustomerDTO customerDTOIn = new CustomerDTO(FIRST_NAME1, LAST_NAME1, "any");
		Customer updatedCustomer = new Customer(ID1, FIRST_NAME1, LAST_NAME1);

		when(customerRepository.save(any())).thenReturn(updatedCustomer);

		CustomerDTO returnedCustomerDTO = customerService.updateCustomer(ID1, customerDTOIn);

		assertEquals(URL1, returnedCustomerDTO.getCustomerUrl());
		assertEquals(FIRST_NAME1, returnedCustomerDTO.getFirstName());
		assertEquals(LAST_NAME1, returnedCustomerDTO.getLastName());
		assertEquals(URL1, returnedCustomerDTO.getCustomerUrl());

	}

	@Test
	public void testDeleteCustomer() {

		customerService.deleteCustomer(ID1);

		verify(customerRepository, times(1)).deleteById(anyLong());
	}

}
