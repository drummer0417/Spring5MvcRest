package nl.androidappfactory.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.v1.mapper.VendorMapper;
import nl.androidappfactory.api.v1.model.VendorDTO;
import nl.androidappfactory.domain.Vendor;
import nl.androidappfactory.repositories.VendorRepository;

@Service
@Slf4j
public class VendorServiceImpl implements VendorService {

	private VendorRepository vendorRepository;
	private VendorMapper vendorMapper;

	public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
		this.vendorRepository = vendorRepository;
		this.vendorMapper = vendorMapper;
	}

	@Override
	public List<VendorDTO> getAllVendors() {
		List<VendorDTO> vendors = vendorRepository.findAll()
				.stream()
				.map(vendor -> convertToDTO(vendor))
				.collect(Collectors.toList());
		return vendors;
	}

	@Override
	public VendorDTO getVendorById(Long id) {

		Optional<Vendor> vendorOptional = vendorRepository.findById(id);
		if (!vendorOptional.isPresent()) {
			log.warn("Vendor not found, id: " + id);
			throw new ResourceNotFoundException("Vendor not found, id: " + id);
		}
		return convertToDTO(vendorOptional.get());
	}

	@Override
	public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {

		Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
		vendor.setId(id);

		return saveVendor(vendor);
	}

	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTOPatch) {

		VendorDTO patchedVendor = vendorRepository.findById(id).map(originalVendor -> applyPatch(originalVendor, vendorDTOPatch))
				.orElseThrow(ResourceNotFoundException::new);
		return patchedVendor;
	}

	@Override
	public VendorDTO createVendor(VendorDTO vendorDTO) {

		return saveVendor(vendorMapper.vendorDTOToVendor(vendorDTO));
	}

	private VendorDTO convertToDTO(Vendor vendor) {
		VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
		vendorDTO.setVendorUrl("/api/v1/vendors/" + vendor.getId());
		return vendorDTO;
	}

	private VendorDTO saveVendor(Vendor vendor) {

		return convertToDTO(vendorRepository.save(vendor));
	}

	private VendorDTO applyPatch(Vendor vendor, VendorDTO vendorDTOPatch) {

		if (vendorDTOPatch.getName() != null) {
			vendor.setName(vendorDTOPatch.getName());
		}
		Vendor savedVendor = vendorRepository.save(vendor);
		return convertToDTO(savedVendor);
	}

}
