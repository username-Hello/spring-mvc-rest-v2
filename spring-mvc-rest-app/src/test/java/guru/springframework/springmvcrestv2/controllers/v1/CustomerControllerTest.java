package guru.springframework.springmvcrestv2.controllers.v1;


import guru.springframework.model.CustomerDTO;
import guru.springframework.springmvcrestv2.services.CustomerService;
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

import static guru.springframework.springmvcrestv2.constants.CoreConstants.BASE_CUSTOMER_URL;

public class CustomerControllerTest extends AbstractRestControllerTest {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "Test_FirstName";
    private static final String LAST_NAME = "Test_LastName";
    public static final String UPDATED_FIRST_NAME = "Updated_FirstName";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        List<CustomerDTO> customerDTOs = Arrays.asList(new CustomerDTO(), new CustomerDTO());
        when(customerService.getAllCustomers()).thenReturn(customerDTOs);

        mockMvc.perform(get(BASE_CUSTOMER_URL).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setCustomerUrl(BASE_CUSTOMER_URL + "/" + ID);
        when(customerService.getCustomerById(any())).thenReturn(customerDTO);

        mockMvc.perform(get(BASE_CUSTOMER_URL + "/" + ID).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(BASE_CUSTOMER_URL + "/" + ID.intValue())));
    }

    @Test
    public void createCustomer() throws Exception {
        CustomerDTO givenCustomer = new CustomerDTO();
        givenCustomer.setFirstName(FIRST_NAME);
        givenCustomer.setLastName(LAST_NAME);

        CustomerDTO returnedCustomer = new CustomerDTO();
        returnedCustomer.setFirstName(givenCustomer.getFirstName());
        returnedCustomer.setLastName(givenCustomer.getLastName());
        returnedCustomer.setCustomerUrl(BASE_CUSTOMER_URL + "/" + ID.intValue());

        when(customerService.createCustomer(any())).thenReturn(returnedCustomer);

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(givenCustomer))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerUrl", equalTo(BASE_CUSTOMER_URL + "/" + ID.intValue())))
                .andExpect(jsonPath(("$.firstName"), equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerDTO givenCustomer = new CustomerDTO();
        givenCustomer.setFirstName(FIRST_NAME);
        givenCustomer.setLastName(LAST_NAME);

        CustomerDTO returnedCustomer = new CustomerDTO();
        returnedCustomer.setFirstName(givenCustomer.getFirstName());
        returnedCustomer.setLastName(givenCustomer.getLastName());
        returnedCustomer.setCustomerUrl(BASE_CUSTOMER_URL + "/" + ID);

        when(customerService.updateCustomer(any(), any())).thenReturn(returnedCustomer);

        mockMvc.perform(put(BASE_CUSTOMER_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(givenCustomer))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerUrl", equalTo(BASE_CUSTOMER_URL + "/" + ID.intValue())))
                .andExpect(jsonPath(("$.firstName"), equalTo(givenCustomer.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(givenCustomer.getLastName())));
    }

    @Test
    public void patchCustomer() throws Exception {
        CustomerDTO givenCustomer = new CustomerDTO();
        givenCustomer.setFirstName(UPDATED_FIRST_NAME);

        CustomerDTO returnedCustomer = new CustomerDTO();
        returnedCustomer.setFirstName(givenCustomer.getFirstName());
        returnedCustomer.setLastName(LAST_NAME);
        returnedCustomer.setCustomerUrl(BASE_CUSTOMER_URL + "/" + ID);

        when(customerService.patchCustomer(any(), any())).thenReturn(returnedCustomer);

        mockMvc.perform(patch(BASE_CUSTOMER_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(givenCustomer))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerUrl", equalTo(BASE_CUSTOMER_URL + "/" + ID.intValue())))
                .andExpect(jsonPath(("$.firstName"), equalTo(givenCustomer.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerService, times(1)).deleteCustomer(anyLong());
    }

}
