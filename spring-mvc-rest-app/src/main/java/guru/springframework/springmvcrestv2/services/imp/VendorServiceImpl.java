package guru.springframework.springmvcrestv2.services.imp;

import guru.springframework.springmvcrestv2.api.v1.VendorDTO;
import guru.springframework.springmvcrestv2.api.v1.mapper.VendorMapper;
import guru.springframework.springmvcrestv2.domain.Vendor;
import guru.springframework.springmvcrestv2.exception.ResourceNotFoundException;
import guru.springframework.springmvcrestv2.repositories.VendorRepository;
import guru.springframework.springmvcrestv2.services.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static guru.springframework.springmvcrestv2.constants.CoreConstants.BASE_VENDOR_URL;

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

    @Override
    public VendorDTO getById(Long id) {
        return vendorRepository.findById(id).map(vendor -> {
            VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
            vendorDTO.setUrl(BASE_VENDOR_URL + "/" + vendor.getId());
            return vendorDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewOne(VendorDTO vendorDTO) {
        return save(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO update(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);
        return save(vendor);
    }

    @Override
    public VendorDTO patch(Long id, VendorDTO vendorDTO) {
        return save(vendorRepository.findById(id).map(originalVendor -> {
            if (vendorDTO.getName() != null) {
                originalVendor.setName(vendorDTO.getName());
            }
            return originalVendor;
        }).orElseThrow(ResourceNotFoundException::new));
    }

    private VendorDTO save(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        vendorDTO.setUrl(BASE_VENDOR_URL + "/" + savedVendor.getId());
        return vendorDTO;
    }

    @Override
    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }

}
