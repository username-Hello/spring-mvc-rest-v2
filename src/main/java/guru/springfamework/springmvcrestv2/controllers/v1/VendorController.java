package guru.springfamework.springmvcrestv2.controllers.v1;

import guru.springfamework.springmvcrestv2.api.v1.VendorDTO;
import guru.springfamework.springmvcrestv2.api.v1.VendorListDTO;
import guru.springfamework.springmvcrestv2.services.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static guru.springfamework.springmvcrestv2.constants.CoreConstants.BASE_VENDOR_URL;

@RestController
@AllArgsConstructor
@RequestMapping(BASE_VENDOR_URL)
public class VendorController {

    private final VendorService vendorService;

    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    private VendorListDTO getListOfVendors() {
        return new VendorListDTO(vendorService.getAll());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    private VendorDTO getVendor(@PathVariable String id) {
        return vendorService.getById(Long.parseLong(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewOne(vendorDTO);
    }
}
