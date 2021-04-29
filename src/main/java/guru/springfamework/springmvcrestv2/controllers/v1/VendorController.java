package guru.springfamework.springmvcrestv2.controllers.v1;

import guru.springfamework.springmvcrestv2.api.v1.VendorDTO;
import guru.springfamework.springmvcrestv2.api.v1.VendorListDTO;
import guru.springfamework.springmvcrestv2.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static guru.springfamework.springmvcrestv2.constants.CoreConstants.BASE_VENDOR_URL;

@Api(tags = {"Vendor"})
@RestController
@AllArgsConstructor
@RequestMapping(BASE_VENDOR_URL)
public class VendorController {

    private final VendorService vendorService;

    @GetMapping( "/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get list of all vendors", notes = "If there's no vendors returns empty list")
    private VendorListDTO getListOfVendors() {
        return new VendorListDTO(vendorService.getAll());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get vendor by ID", notes = "If there's no vendors return Resource Not Found exception")
    private VendorDTO getVendor(@PathVariable String id) {
        return vendorService.getById(Long.parseLong(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new vendor", notes = "Requires first and last names of vendor")
    private VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewOne(vendorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update vendor completely",
            notes = "If there's no vendor with given ID return Resource Not Found exception")
    public VendorDTO updateVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.update(Long.parseLong(id), vendorDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update vendor partly",
            notes = "If there's no vendor with given ID return Resource Not Found exception")
    public VendorDTO patchVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patch(Long.parseLong(id), vendorDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete vendor by ID",
            notes = "If there's no vendor with given ID return Resource Not Found exception")
    public void deleteVendorById(@PathVariable String id) {
        vendorService.deleteById(Long.parseLong(id));
    }
}
