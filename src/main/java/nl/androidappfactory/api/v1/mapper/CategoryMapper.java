package nl.androidappfactory.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import nl.androidappfactory.api.v1.model.CategoryDTO;
import nl.androidappfactory.domain.Category;

@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	@Mapping(source = "lang", target = "taal") // Mapping is only needed if fieldnames are different
	CategoryDTO categoryToCategoryDTO(Category category);

	Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
