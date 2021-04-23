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
import static guru.springfamework.springmvcrestv2.services.imp.VendorServiceIT.UPDATED_NAME;
import static guru.springfamework.springmvcrestv2.services.imp.VendorServiceImplTest.ID;
import static guru.springfamework.springmvcrestv2.services.imp.VendorServiceImplTest.TEST_NAME;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class VendorControllerTest extends AbstractRestControllerTest {

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

    @Test
    public void getVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(TEST_NAME);
        vendorDTO.setUrl(BASE_VENDOR_URL + "/" + ID);
        when(vendorService.getById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get(BASE_VENDOR_URL + "/" + ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO.getName())))
                .andExpect(jsonPath("$.vendor_url", equalTo(vendorDTO.getUrl())));
    }

    @Test
    public void createNewVendor() throws Exception {
        VendorDTO givenVendor = new VendorDTO();
        givenVendor.setName(TEST_NAME);

        VendorDTO returnedVendor = new VendorDTO();
        returnedVendor.setName(givenVendor.getName());
        returnedVendor.setUrl(BASE_VENDOR_URL + "/" + ID);

        when(vendorService.createNewOne(any())).thenReturn(returnedVendor);

        mockMvc.perform(post(BASE_VENDOR_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(givenVendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vendor_url", equalTo(returnedVendor.getUrl())))
                .andExpect(jsonPath("$.name", equalTo(returnedVendor.getName())));
    }

    @Test
    public void updateVendor() throws Exception {
        Long givenId = ID;
        VendorDTO givenVendor = new VendorDTO();
        givenVendor.setName(TEST_NAME);

        VendorDTO returnedVendor = new VendorDTO();
        returnedVendor.setName(givenVendor.getName());
        returnedVendor.setUrl(BASE_VENDOR_URL + "/" + givenId);

        when(vendorService.update(anyLong(), any())).thenReturn(returnedVendor);

        mockMvc.perform(put(BASE_VENDOR_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(givenVendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendor_url", equalTo(returnedVendor.getUrl())))
                .andExpect(jsonPath("$.name", equalTo(returnedVendor.getName())));
    }

    @Test
    public void patchVendor() throws Exception {
        Long givenId = ID;
        VendorDTO givenVendor = new VendorDTO();
        givenVendor.setName(UPDATED_NAME);

        VendorDTO patchedVendor = new VendorDTO();
        patchedVendor.setName(givenVendor.getName());
        patchedVendor.setUrl(BASE_VENDOR_URL + "/" + givenId);

        when(vendorService.patch(anyLong(), any())).thenReturn(patchedVendor);

        mockMvc.perform(patch(BASE_VENDOR_URL + "/" + givenId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(givenVendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendor_url", equalTo(patchedVendor.getUrl())))
                .andExpect(jsonPath("$.name", equalTo(patchedVendor.getName())));
    }

    @Test
    public void deleteVendorById() throws Exception {
        mockMvc.perform(delete(BASE_VENDOR_URL + "/" + ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(vendorService, times(1)).deleteById(anyLong());
    }
}