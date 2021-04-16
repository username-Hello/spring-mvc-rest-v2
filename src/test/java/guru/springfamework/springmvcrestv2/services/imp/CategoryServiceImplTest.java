package guru.springfamework.springmvcrestv2.services.imp;

import guru.springfamework.springmvcrestv2.api.v2.CategoryDTO;
import guru.springfamework.springmvcrestv2.api.v2.mapper.CategoryMapper;
import guru.springfamework.springmvcrestv2.domain.Category;
import guru.springfamework.springmvcrestv2.repositories.CategoryRepository;
import guru.springfamework.springmvcrestv2.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    public static final String CATEGORY_NAME = "Test Category Name";
    public static final long ID = 123L;
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() {
        List<Category> categories = new ArrayList<>(Arrays.asList(new Category(), new Category()));
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();

        assertEquals(categories.size(), categoryDTOs.size());
    }

    @Test
    public void getCategoryByName() {
        Category category = new Category();
        category.setName(CATEGORY_NAME);
        category.setId(ID);

        when(categoryRepository.findCategoryByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(CATEGORY_NAME);

        assertEquals(categoryDTO.getName(), CATEGORY_NAME);
    }
}