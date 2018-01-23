package nl.androidappfactory.api.v1.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import nl.androidappfactory.api.v1.controller.RestResponseEntityExceptionHandler;
import nl.androidappfactory.api.v1.controller.VendorController;
import nl.androidappfactory.api.v1.model.VendorDTO;
import nl.androidappfactory.services.VendorService;

public class VendorControllerTest {

	private static final String NAME1 = "name1";
	private static final String NAME2 = "name2";

	private static final String URL1 = getBaseUrl() + "/1";
	private static final String URL2 = getBaseUrl() + "/2";

	@Mock
	VendorService vendorService;

	VendorController vendorController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		vendorController = new VendorController(vendorService);

		mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();

	}

	@Test
	public void testGetAllVendors() throws Exception {

		List<VendorDTO> vendorDTOs = new ArrayList<>();
		vendorDTOs.add(new VendorDTO(NAME1, URL1));
		vendorDTOs.add(new VendorDTO(NAME2, URL2));

		when(vendorService.getAllVendors()).thenReturn(vendorDTOs);

		mockMvc.perform(get(getBaseUrl())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.vendors", hasSize(2)));
	}

	private static String getBaseUrl() {
		return VendorController.BASE_URL;
	}
}
