package nl.androidappfactory.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.v1.mapper.CustomerMapper;
import nl.androidappfactory.api.v1.model.CustomerDTO;
import nl.androidappfactory.bootstrap.Bootstrap;
import nl.androidappfactory.domain.Customer;
import nl.androidappfactory.repositories.CategoryRepository;
import nl.androidappfactory.repositories.CustomerRepository;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceIT {

	private final static String FIRST_NAME = "theFirstName";
	private final static String LAST_NAME = "theLastName";

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CategoryRepository categoryRepository;

	CustomerService customerService;

	Customer customerBeforeUpdate;

	@Before
	public void setUp() throws Exception {

		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);

		bootstrap.run("any");

		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);

		customerBeforeUpdate = customerRepository.findAll().get(0);
	}

	@Test
	public void testPatchFirstName() {

		CustomerDTO customerDTOIn = new CustomerDTO(FIRST_NAME, null, null);

		CustomerDTO customerDTOUpated = customerService.patchCustomer(customerBeforeUpdate.getId(), customerDTOIn);

		assertEquals(FIRST_NAME, customerDTOUpated.getFirstName());
		assertEquals(customerBeforeUpdate.getLastName(), customerDTOUpated.getLastName());
		assertEquals("/api/v1/customers/" + customerBeforeUpdate.getId(), customerDTOUpated.getCustomerUrl());

		log.debug("after update: " + customerDTOUpated);
	}

	@Test
	public void testPatchLastName() {

		CustomerDTO customerDTOIn = new CustomerDTO(null, LAST_NAME, null);

		CustomerDTO customerDTOUpated = customerService.patchCustomer(customerBeforeUpdate.getId(), customerDTOIn);

		assertEquals(LAST_NAME, customerDTOUpated.getLastName());
		assertEquals(customerBeforeUpdate.getFirstName(), customerDTOUpated.getFirstName());
		assertEquals("/api/v1/customers/" + customerBeforeUpdate.getId(), customerDTOUpated.getCustomerUrl());

		log.debug("after update: " + customerDTOUpated);
	}

	@Test
	public void testPatchFirstAndLastName() {

		CustomerDTO customerDTOIn = new CustomerDTO(FIRST_NAME, LAST_NAME, null);

		CustomerDTO customerDTOUpated = customerService.patchCustomer(customerBeforeUpdate.getId(), customerDTOIn);

		assertEquals(FIRST_NAME, customerDTOUpated.getFirstName());
		assertEquals(LAST_NAME, customerDTOUpated.getLastName());
		assertEquals("/api/v1/customers/" + customerBeforeUpdate.getId(), customerDTOUpated.getCustomerUrl());

		log.debug("after update: " + customerDTOUpated);
	}
}
