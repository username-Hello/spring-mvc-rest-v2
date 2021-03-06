package guru.springframework.springmvcrestv2.services.imp;

import guru.springframework.springmvcrestv2.api.v1.VendorDTO;
import guru.springframework.springmvcrestv2.api.v1.mapper.VendorMapper;
import guru.springframework.springmvcrestv2.domain.Vendor;
import guru.springframework.springmvcrestv2.exception.ResourceNotFoundException;
import guru.springframework.springmvcrestv2.repositories.VendorRepository;
import guru.springframework.springmvcrestv2.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static guru.springframework.springmvcrestv2.constants.CoreConstants.BASE_VENDOR_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    public static final String TEST_NAME = "test name";
    public static final long ID = 1L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getAll() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendors);
        List<VendorDTO> result = vendorService.getAll();
        assertEquals(vendors.size(), result.size());
        assertNotNull(result.get(0).getUrl());
    }

    @Test
    public void getById() {
        Vendor vendor = new Vendor(ID, TEST_NAME);
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO result = vendorService.getById(ID);
        assertEquals(vendor.getName(), result.getName());
        assertEquals(BASE_VENDOR_URL + "/" + vendor.getId(), result.getUrl());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getByWrongId() {
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.empty());
        vendorService.getById(ID);
    }

    @Test
    public void createNewOne() {
        VendorDTO givenVendor = new VendorDTO();
        givenVendor.setName(TEST_NAME);

        Vendor returnedVendor = new Vendor(ID, givenVendor.getName());
        when(vendorRepository.save(any())).thenReturn(returnedVendor);

        VendorDTO result = vendorService.createNewOne(givenVendor);
        assertNotNull(result);
        assertEquals(BASE_VENDOR_URL + "/" + returnedVendor.getId(), result.getUrl());
        assertEquals(TEST_NAME, result.getName());
    }

    @Test
    public void update() {
        Long givenId = ID;
        VendorDTO givenVendor = new VendorDTO();
        givenVendor.setName(TEST_NAME);

        Vendor returnedVendor = new Vendor(givenId, givenVendor.getName());
        when(vendorRepository.save(any())).thenReturn(returnedVendor);

        VendorDTO result = vendorService.update(givenId, givenVendor);
        assertNotNull(result);
        assertEquals(BASE_VENDOR_URL + "/" + givenId, result.getUrl());
        assertEquals(TEST_NAME, result.getName());
    }

    @Test
    public void deleteById() {
        vendorService.deleteById(ID);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}