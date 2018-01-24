package nl.androidappfactory.api.v1.controllers;

import static nl.androidappfactory.Helpers.Helper.asJsonString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import nl.androidappfactory.api.v1.controller.RestResponseEntityExceptionHandler;
import nl.androidappfactory.api.v1.controller.VendorController;
import nl.androidappfactory.api.v1.model.VendorDTO;
import nl.androidappfactory.services.VendorService;

public class VendorControllerTest {

	private static final String NAME1 = "name1";
	private static final String NAME2 = "name2";
	private static final String PATCHED_NAME = "a new name";

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

	@Test
	public void testGetVendorById() throws Exception {

		when(vendorService.getVendorById(anyLong())).thenReturn(new VendorDTO(NAME1, URL1));

		mockMvc.perform(get(getBaseUrl() + "/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(NAME1)))
				.andExpect(jsonPath("$.vendor_url", equalTo(URL1)));
	}

	@Test
	public void testCreateVendor() throws Exception {

		when(vendorService.createVendor(any())).thenReturn(new VendorDTO(NAME1, URL1));

		mockMvc.perform(post(getBaseUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(new VendorDTO(NAME1, null))))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", equalTo(NAME1)))
				.andExpect(jsonPath("$.vendor_url", equalTo(URL1)));
	}

	@Test
	public void testUpdateVendor() throws Exception {

		when(vendorService.updateVendor(anyLong(), any())).thenReturn(new VendorDTO(NAME2, URL2));

		mockMvc.perform(put(getBaseUrl() + "/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(new VendorDTO(NAME1, null))))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(NAME2)))
				.andExpect(jsonPath("$.vendor_url", equalTo(URL2)));

	}

	@Test
	public void testPatchVendor() throws Exception {

		when(vendorService.patchVendor(anyLong(), any())).thenReturn(new VendorDTO(PATCHED_NAME, URL2));

		mockMvc.perform(patch(getBaseUrl() + "/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(new VendorDTO(NAME1, null))))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(PATCHED_NAME)))
				.andExpect(jsonPath("$.vendor_url", equalTo(URL2)));
	}

	@Test
	public void testDeleteVendor() throws Exception {

		mockMvc.perform(delete(getBaseUrl() + "/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testPatchVendorNoChanges() throws Exception {

		when(vendorService.patchVendor(anyLong(), any())).thenReturn(new VendorDTO(NAME2, URL2));

		mockMvc.perform(patch(getBaseUrl() + "/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(new VendorDTO())))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(NAME2)))
				.andExpect(jsonPath("$.vendor_url", equalTo(URL2)));
	}

	private static String getBaseUrl() {
		return VendorController.BASE_URL;
	}

}
