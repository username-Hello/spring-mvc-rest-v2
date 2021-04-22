package guru.springfamework.springmvcrestv2.services.imp;

import guru.springfamework.springmvcrestv2.api.v1.VendorDTO;
import guru.springfamework.springmvcrestv2.api.v1.mapper.VendorMapper;
import guru.springfamework.springmvcrestv2.repositories.VendorRepository;
import guru.springfamework.springmvcrestv2.services.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static guru.springfamework.springmvcrestv2.constants.CoreConstants.BASE_VENDOR_URL;

@Service
@AllArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    public List<VendorDTO> getAll(){
        return vendorRepository.findAll().stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setUrl(BASE_VENDOR_URL + "/" + vendor.getId());
                    return vendorDTO;
                }).collect(Collectors.toList());
    }
}
