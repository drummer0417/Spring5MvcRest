package nl.androidappfactory.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import nl.androidappfactory.api.v1.mapper.CategoryMapper;
import nl.androidappfactory.api.v1.model.CategoryDTO;
import nl.androidappfactory.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	CategoryRepository categoryRepository;
	CategoryMapper categoryMapper;

	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {

		// List<Category> categories = categoryRepository.findAll();
		// List<CategoryDTO> categoryDTOs = new ArrayList<>();
		//
		// for (Category category : categories) {
		// categoryDTOs.add(categoryMapper.categoryToCategoryDTO(category));
		// }
		//
		// return categoryDTOs;

		return categoryRepository.findAll()
				.stream()
				.map(categoryMapper::categoryToCategoryDTO) // same as ++>>.map(category ->
															// categoryMapper.categoryToCategoryDTO(category))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {

		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}

}
