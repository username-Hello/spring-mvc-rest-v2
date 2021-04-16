package guru.springfamework.springmvcrestv2.api.v2.mapper;

import guru.springfamework.springmvcrestv2.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import guru.springfamework.springmvcrestv2.api.v2.CategoryDTO;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDto(Category category);
}
