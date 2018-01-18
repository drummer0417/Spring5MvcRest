package nl.androidappfactory.services;

import java.util.List;

import nl.androidappfactory.api.v1.model.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getAllCategories();

	CategoryDTO getCategoryByName(String name);

}
