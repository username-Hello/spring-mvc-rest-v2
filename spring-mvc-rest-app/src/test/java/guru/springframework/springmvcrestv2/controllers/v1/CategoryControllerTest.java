package guru.springframework.springmvcrestv2.controllers.v1;

import guru.springframework.springmvcrestv2.api.v1.CategoryDTO;
import guru.springframework.springmvcrestv2.services.CategoryService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    public static final String NAME = "Jim";
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void getAllCategories() throws Exception {
        List<CategoryDTO> categoryDTOList = Arrays.asList(new CategoryDTO(), new CategoryDTO());
        when(categoryService.getAllCategories()).thenReturn(categoryDTOList);

        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void name() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(NAME);

        when(categoryService.getCategoryByName(any())).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/v1/categories/Jim")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));

    }
}