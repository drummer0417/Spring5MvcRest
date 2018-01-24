package nl.androidappfactory.api.v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nl.androidappfactory.api.v1.model.VendorDTO;
import nl.androidappfactory.api.v1.model.VendorListDTO;
import nl.androidappfactory.services.VendorService;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

	public final static String BASE_URL = "/api/v1/vendors";

	private VendorService vendorService;

	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public VendorListDTO getAllVendors() {

		VendorListDTO vendors = new VendorListDTO(vendorService.getAllVendors());
		return vendors;
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendorById(@PathVariable String id) {

		VendorDTO vendorDTO = vendorService.getVendorById(new Long(id));
		return vendorDTO;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO) {

		VendorDTO createdVendor = vendorService.createVendor(vendorDTO);
		return createdVendor;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO updateVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO) {

		VendorDTO updateVendor = vendorService.updateVendor(new Long(id), vendorDTO);
		return updateVendor;
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO patchVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO) {

		VendorDTO patchedVendor = vendorService.patchVendor(new Long(id), vendorDTO);
		return patchedVendor;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteVendor(@PathVariable String id) {

		vendorService.deleteVendor(new Long(id));
	}

}
