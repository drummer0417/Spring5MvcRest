package nl.androidappfactory.api.v1.controllers;

import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.v1.controller.CategoryController;
import nl.androidappfactory.api.v1.model.CategoryDTO;
import nl.androidappfactory.services.CategoryService;

@Slf4j
public class CategoryControllerTest {

	private static final Long ID1 = 1l;
	private static final Long ID2 = 2l;
	private static final String NAME1 = "name1";
	private static final String NAME2 = "name2";

	@Mock
	CategoryService categoryService;

	@InjectMocks // injects the categoryService into the controller constructor
	CategoryController categoryController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}

	@Test
	public void testListCategories() throws Exception {

		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		CategoryDTO cat1 = new CategoryDTO();
		cat1.setId(ID1);
		cat1.setName(NAME1);
		CategoryDTO cat2 = new CategoryDTO();
		cat2.setId(ID2);
		cat2.setName(NAME2);
		categoryDTOs.add(cat1);
		categoryDTOs.add(cat2);

		when(categoryService.getAllCategories()).thenReturn(categoryDTOs);

		mockMvc.perform(get("/api/v1/categories/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.categories", hasSize(2)));

	}

	@Test
	public void testGetCategoryByName() throws Exception {

		CategoryDTO cat = new CategoryDTO();
		cat.setId(ID1);
		cat.setName(NAME1);

		when(categoryService.getCategoryByName(anyString())).thenReturn(cat);

		mockMvc.perform(get("/api/v1/categories/" + NAME1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(NAME1)));
	}
}
