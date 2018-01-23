package nl.androidappfactory.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nl.androidappfactory.api.v1.model.VendorDTO;
import nl.androidappfactory.domain.Vendor;

public class VendorMapperTest {

	public final static Long ID = 1l;
	public final static String NAME = "Company";

	private VendorMapper mapper = VendorMapper.INSTANCE;

	@Before
	public void setUp() throws Exception {}

	@Test
	public void VendorToVendorDTO() {

		Vendor vendor = new Vendor(ID, NAME);

		VendorDTO vendorDTO = mapper.vendorToVendorDTO(vendor);

		assertEquals(NAME, vendorDTO.getName());

	}

	@Test
	public void VendorDTOToVendor() {

		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(NAME);

		Vendor vendor = mapper.vendorDTOToVendor(vendorDTO);

		assertEquals(NAME, vendor.getName());
	}

}
