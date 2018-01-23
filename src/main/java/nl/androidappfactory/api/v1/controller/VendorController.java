package nl.androidappfactory.api.v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
