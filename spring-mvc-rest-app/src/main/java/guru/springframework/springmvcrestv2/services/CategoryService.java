package guru.springframework.springmvcrestv2.services;

import guru.springframework.springmvcrestv2.api.v1.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);

}
