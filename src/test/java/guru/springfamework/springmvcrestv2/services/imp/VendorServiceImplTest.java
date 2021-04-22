package guru.springfamework.springmvcrestv2.services.imp;

import guru.springfamework.springmvcrestv2.api.v1.VendorDTO;
import guru.springfamework.springmvcrestv2.api.v1.mapper.VendorMapper;
import guru.springfamework.springmvcrestv2.domain.Vendor;
import guru.springfamework.springmvcrestv2.repositories.VendorRepository;
import guru.springfamework.springmvcrestv2.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp( {
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
}