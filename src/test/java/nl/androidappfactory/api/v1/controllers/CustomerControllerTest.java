package nl.androidappfactory.api.v1.controllers;

import static nl.androidappfactory.Helpers.Helper.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.v1.controller.CustomerController;
import nl.androidappfactory.api.v1.model.CustomerDTO;
import nl.androidappfactory.services.CustomerService;;

@Slf4j
public class CustomerControllerTest {

	private static final Long ID1 = new Long(1);
	private static final Long ID2 = new Long(2);
	private static final String FIRST_NAME1 = "FN1";
	private static final String FIRST_NAME2 = "FN2";
	private static final String LAST_NAME1 = "LN1";
	private static final String LAST_NAME2 = "LN2";
	private static final String URL1 = "/api/v1/customers/1";
	private static final String URL2 = "/api/v1/customers/2";

	@Mock
	CustomerService customerService;

	MockMvc mockMvc;

	CustomerController customerController;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		customerController = new CustomerController(customerService);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testFindAllCustomers() throws Exception {

		List<CustomerDTO> customerDTOs = new ArrayList<>();
		customerDTOs.add(new CustomerDTO(FIRST_NAME1, LAST_NAME1, URL1));
		customerDTOs.add(new CustomerDTO(FIRST_NAME2, LAST_NAME2, URL2));

		when(customerService.getAllCustomers()).thenReturn(customerDTOs);

		mockMvc.perform(get("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", hasSize(2)));
	}

	@Test
	public void testFindCustomerById() throws Exception {

		CustomerDTO cutomerDTO = (new CustomerDTO(FIRST_NAME1, LAST_NAME1, URL1));

		when(customerService.getCustomerById(anyLong())).thenReturn(cutomerDTO);

		mockMvc.perform(get("/api/v1/customers/" + ID1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo("FN1")));
	}

	@Test
	public void testCreateCustomers() throws Exception {

		CustomerDTO customerIn = new CustomerDTO(FIRST_NAME1, LAST_NAME1, null);

		CustomerDTO customerCreated = new CustomerDTO(FIRST_NAME1, LAST_NAME1, URL1);
		String customerAsJson = asJsonString(customerIn);

		log.debug("before create: " + customerAsJson);

		when(customerService.createCustomer(any())).thenReturn(customerCreated);

		mockMvc.perform(post("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerAsJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME1)))
				.andExpect(jsonPath("$.customer_url", equalTo(URL1)));
	}

	@Test
	public void testUpdateCustomers() throws Exception {

		CustomerDTO customerIn = new CustomerDTO(FIRST_NAME1, LAST_NAME1, null);

		CustomerDTO customerUpdated = new CustomerDTO(FIRST_NAME1, LAST_NAME1, URL1);
		String customerAsJson = asJsonString(customerIn);

		log.debug("before create: " + customerAsJson);

		when(customerService.updateCustomer(anyLong(), any())).thenReturn(customerUpdated);

		mockMvc.perform(put("/api/v1/customers/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerAsJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME1)))
				.andExpect(jsonPath("$.customer_url", equalTo(URL1)));
	}

	@Test
	public void testPatchCustomers() throws Exception {

		CustomerDTO customerIn = new CustomerDTO(FIRST_NAME1, null, null);

		CustomerDTO customerUpdated = new CustomerDTO(FIRST_NAME1, LAST_NAME1, URL1);
		String customerAsJson = asJsonString(customerIn);

		log.debug("before create: " + customerAsJson);

		when(customerService.patchCustomer(anyLong(), any())).thenReturn(customerUpdated);

		mockMvc.perform(patch("/api/v1/customers/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerAsJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME1)))
				.andExpect(jsonPath("$.customer_url", equalTo(URL1)));
	}

}
