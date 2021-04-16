package guru.springfamework.springmvcrestv2.services;

import guru.springfamework.springmvcrestv2.api.v2.CategoryDTO;
import guru.springfamework.springmvcrestv2.domain.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);

}
