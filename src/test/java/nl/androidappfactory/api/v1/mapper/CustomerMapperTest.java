package nl.androidappfactory.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nl.androidappfactory.api.v1.model.CustomerDTO;
import nl.androidappfactory.domain.Customer;

public class CustomerMapperTest {

	public final static Long ID = 1l;
	public final static String FIRST_NAME = "Hans";
	public final static String LAST_NAME = "van Meurs";

	private CustomerMapper mapper = CustomerMapper.INSTANCE;

	@Before
	public void setUp() throws Exception {}

	@Test
	public void CustomerToCustomerDTO() {

		Customer customer = new Customer(ID, FIRST_NAME, LAST_NAME);

		CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);

		assertEquals(customerDTO.getId(), ID);
		assertEquals(customerDTO.getFirstName(), FIRST_NAME);
		assertEquals(customerDTO.getLastName(), LAST_NAME);
	}

	@Test
	public void CustomerDTOToCustomer() {

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(ID);
		customerDTO.setFirstName(FIRST_NAME);
		customerDTO.setLastName(LAST_NAME);

		Customer customer = mapper.customerDTOToCustomer(customerDTO);

		assertEquals(customer.getId(), ID);
		assertEquals(customer.getFirstName(), FIRST_NAME);
		assertEquals(customer.getLastName(), LAST_NAME);
	}

}
