package nl.androidappfactory.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.androidappfactory.api.v1.controller.VendorController;
import nl.androidappfactory.api.v1.mapper.VendorMapper;
import nl.androidappfactory.api.v1.model.VendorDTO;
import nl.androidappfactory.domain.Vendor;
import nl.androidappfactory.repositories.VendorRepository;

public class VendorServiiceTest {

	private final static Long ID1 = 1l;
	private final static String NAME1 = "name1";
	private final static Long ID2 = 2l;
	private final static String NAME2 = "name2";
	private final static String URL1 = getBaseURL() + "/1";

	@Mock
	VendorRepository vendorRepository;

	VendorMapper vendorMapper = VendorMapper.INSTANCE;

	VendorService vendorService;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);
	}

	@Test
	public void testGetAllVendors() {

		List<Vendor> vendors = new ArrayList<>();
		Vendor vendor1 = new Vendor(ID1, NAME1);
		Vendor vendor2 = new Vendor(ID2, NAME2);

		vendors.add(vendor1);
		vendors.add(vendor2);

		when(vendorRepository.findAll()).thenReturn(vendors);

		List<VendorDTO> vendorsReturned = vendorService.getAllVendors();

		assertEquals(2, vendorsReturned.size());
		assertEquals(NAME1, vendorsReturned.get(0).getName());
		assertEquals(URL1, vendorsReturned.get(0).getVendorUrl());
	}

	@Test
	public void testGetVendorById() {

		Vendor vendor1 = new Vendor(ID1, NAME1);

		when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor1));

		VendorDTO vendorReturned = vendorService.getVendorById(1l);

		assertEquals(NAME1, vendorReturned.getName());
		assertEquals(URL1, vendorReturned.getVendorUrl());

	}

	@Test
	public void testUpdateVendor() {

		Vendor vendor1 = new Vendor(ID1, NAME1);

		when(vendorRepository.save(any())).thenReturn(vendor1);

		VendorDTO vendorReturned = vendorService.updateVendor(1l, new VendorDTO());

		assertEquals(NAME1, vendorReturned.getName());
		assertEquals(URL1, vendorReturned.getVendorUrl());

	}

	// @Test
	// public void testPatchVendor() {
	//
	// when(vendorRepository.save(any())).thenReturn(vendor2);
	//
	// VendorDTO vendorDTOReturned = vendorService.patchVendor(1l, vendorDTO1);
	//
	// assertEquals(vendor1.getName(), vendorDTOReturned.getName());
	// assertEquals(getBaseURL() + "/1", vendorDTOReturned.getVendorUrl());
	//
	// }

	@Test
	public void testCreateVendor() {}

	private static String getBaseURL() {
		return VendorController.BASE_URL;
	}
}
