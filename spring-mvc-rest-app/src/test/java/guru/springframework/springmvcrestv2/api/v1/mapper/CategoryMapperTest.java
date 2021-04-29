package guru.springframework.springmvcrestv2.api.v1.mapper;

import guru.springframework.springmvcrestv2.api.v1.CategoryDTO;
import guru.springframework.springmvcrestv2.domain.Category;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String Name = "Fruit";
    public static final long ID = 123L;

    @Test
    public void mapToDtoTest() {
        Category category = new Category();
        category.setName(Name);
        category.setId(ID);

        CategoryDTO categoryDTO = Mappers.getMapper(CategoryMapper.class).categoryToCategoryDto(category);

        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(Name, categoryDTO.getName());

    }
}