package nl.androidappfactory.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.v1.mapper.VendorMapper;
import nl.androidappfactory.api.v1.model.VendorDTO;
import nl.androidappfactory.bootstrap.Bootstrap;
import nl.androidappfactory.domain.Vendor;
import nl.androidappfactory.repositories.CategoryRepository;
import nl.androidappfactory.repositories.CustomerRepository;
import nl.androidappfactory.repositories.VendorRepository;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class VendorServiiceITTest {

	VendorService vendorService;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	VendorRepository vendorRepository;

	VendorMapper vendorMapper = VendorMapper.INSTANCE;

	@Before
	public void setUp() throws Exception {

		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);

		bootstrap.run("any");

		vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);

	}

	@Test
	public void testTestPatchVendor() {

		Vendor vendorBeforeUpdate = vendorRepository.findAll().get(0);

		String vendorNameOld = vendorBeforeUpdate.getName();

		log.debug("name before update: " + vendorBeforeUpdate.getName());

		VendorDTO vendorPatch = new VendorDTO("A new company name", null);

		VendorDTO patchedVendor = vendorService.patchVendor(vendorBeforeUpdate.getId(), vendorPatch);

		log.debug("before assert: " + vendorNameOld);
		assertNotEquals(vendorNameOld, patchedVendor.getName());
		assertEquals(vendorPatch.getName(), patchedVendor.getName());
	}
}
