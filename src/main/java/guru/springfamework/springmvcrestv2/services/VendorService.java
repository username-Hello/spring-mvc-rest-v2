package guru.springfamework.springmvcrestv2.services;

import guru.springfamework.springmvcrestv2.api.v1.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAll();

    VendorDTO getById(Long id);

    VendorDTO createNewOne(VendorDTO vendorDTO);

    VendorDTO update(Long id, VendorDTO vendorDTO);

    VendorDTO patch(Long id, VendorDTO vendorDTO);

    void deleteById(Long id);
}
