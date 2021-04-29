package guru.springframework.springmvcrestv2.api.v1.mapper;

import guru.springframework.springmvcrestv2.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import guru.springframework.springmvcrestv2.api.v1.CategoryDTO;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDto(Category category);
}
