package guru.springfamework.springmvcrestv2.api.v1.mapper;

import guru.springfamework.springmvcrestv2.api.v1.VendorDTO;
import guru.springfamework.springmvcrestv2.domain.Vendor;
import org.mapstruct.Mapper;

@Mapper
public interface VendorMapper {

    public VendorDTO vendorToVendorDTO(Vendor source);

    public Vendor vendorDTOToVendor(VendorDTO source);
}
