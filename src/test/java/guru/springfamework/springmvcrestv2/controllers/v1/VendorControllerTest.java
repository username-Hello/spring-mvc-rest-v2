package guru.springfamework.springmvcrestv2.controllers.v1;

import guru.springfamework.springmvcrestv2.api.v1.VendorDTO;
import guru.springfamework.springmvcrestv2.exception.RestResponseEntityExceptionHandler;
import guru.springfamework.springmvcrestv2.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.springfamework.springmvcrestv2.constants.CoreConstants.BASE_VENDOR_URL;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


public class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getListOfVendors() throws Exception {
        List<VendorDTO> vendors = Arrays.asList(new VendorDTO(), new VendorDTO());
        when(vendorService.getAll()).thenReturn(vendors);

        mockMvc.perform(get(BASE_VENDOR_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }
}