package guru.springframework.springmvcrestv2.services.imp;

import guru.springframework.springmvcrestv2.api.v1.CategoryDTO;
import guru.springframework.springmvcrestv2.api.v1.mapper.CategoryMapper;
import guru.springframework.springmvcrestv2.repositories.CategoryRepository;
import guru.springframework.springmvcrestv2.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.findCategoryByName(name));
    }


}
