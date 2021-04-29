package guru.springframework.springmvcrestv2.api.v1.mapper;

import guru.springframework.springmvcrestv2.api.v1.VendorDTO;
import guru.springframework.springmvcrestv2.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor source);

    Vendor vendorDTOToVendor(VendorDTO source);
}
