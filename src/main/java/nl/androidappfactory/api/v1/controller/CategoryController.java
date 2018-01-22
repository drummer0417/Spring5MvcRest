package nl.androidappfactory.api.v1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.v1.model.CategoryDTO;
import nl.androidappfactory.api.v1.model.CategoryListDTO;
import nl.androidappfactory.services.CategoryService;

@Slf4j
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

	public final static String BASE_URL = "/api/v1/categories";

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public CategoryListDTO findAllCategories() {

		log.debug("in findAllCategories: ");

		List<CategoryDTO> categories = categoryService.getAllCategories();

		return new CategoryListDTO(categories);
	}

	@GetMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO findCategoryByName(@PathVariable String name) {

		log.debug("in findCategoryByName: ");

		CategoryDTO category = categoryService.getCategoryByName(name);

		log.debug("after findCategoryByName: " + category);
		return category;
	}
}
