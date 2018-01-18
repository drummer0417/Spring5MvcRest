package nl.androidappfactory.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.androidappfactory.api.v1.mapper.CustomerMapper;
import nl.androidappfactory.api.v1.model.CustomerDTO;
import nl.androidappfactory.domain.Customer;
import nl.androidappfactory.repositories.CustomerRepository;

public class CustomerSeriviceTest {

	private static final Long ID1 = 1l;
	private static final Long ID2 = 2l;
	private static final String FIRST_NAME1 = "FN1";
	private static final String FIRST_NAME2 = "FN2";
	private static final String LAST_NAME1 = "LN1";
	private static final String LAST_NAME2 = "LN2";

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
	public void getCustomerByFirstName() {

		Customer customer = new Customer(ID1, FIRST_NAME1, LAST_NAME1);

		when(customerRepository.findByFirstName(FIRST_NAME1)).thenReturn(customer);

		CustomerDTO customerDTO = customerService.getCustomerByFirstName(FIRST_NAME1);

		assertEquals(ID1, customerDTO.getId());
		assertEquals(FIRST_NAME1, customerDTO.getFirstName());
		assertEquals(LAST_NAME1, customerDTO.getLastName());

	}

}
