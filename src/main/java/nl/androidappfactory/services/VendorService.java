package nl.androidappfactory.services;

import java.util.List;

import nl.androidappfactory.api.v1.model.VendorDTO;

public interface VendorService {

	public VendorDTO getVendorById(Long id);

	public VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

	public VendorDTO createVendor(VendorDTO vendorDTO);

	public List<VendorDTO> getAllVendors();

}
