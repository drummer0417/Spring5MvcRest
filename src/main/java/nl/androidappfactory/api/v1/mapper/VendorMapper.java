package nl.androidappfactory.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import nl.androidappfactory.api.v1.model.VendorDTO;
import nl.androidappfactory.domain.Vendor;

@Mapper
public interface VendorMapper {

	public VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

	public VendorDTO vendorToVendorDTO(Vendor vendor);

	public Vendor vendorDTOToVendor(VendorDTO vendorDTO);

}
