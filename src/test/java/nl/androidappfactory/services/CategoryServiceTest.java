package nl.androidappfactory.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.androidappfactory.api.v1.mapper.CategoryMapper;
import nl.androidappfactory.api.v1.model.CategoryDTO;
import nl.androidappfactory.domain.Category;
import nl.androidappfactory.repositories.CategoryRepository;

public class CategoryServiceTest {

	private static final Long ID1 = 1l;
	private static final Long ID2 = 2l;
	private static final String NAME1 = "name1";
	private static final String NAME2 = "name2";

	CategoryService categoryService;

	@Mock
	CategoryRepository categoryRepository;

	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
	}

	@Test
	public void getAllCategoriesTest() {

		List<Category> categories = new ArrayList<>();
		Category cat1 = new Category();
		cat1.setId(ID1);
		cat1.setName(NAME1);
		Category cat2 = new Category();
		cat2.setId(ID2);
		cat2.setName(NAME2);
		categories.add(cat1);
		categories.add(cat2);

		when(categoryRepository.findAll()).thenReturn(categories);

		List<CategoryDTO> readCategories = categoryService.getAllCategories();

		assertNotNull(readCategories);
		assertTrue(readCategories.size() == 2);
		assertEquals(NAME1, readCategories.get(0).getName());
	}

	@Test
	public void getCategoryByName() {

		Category cat1 = new Category();
		cat1.setId(ID1);
		cat1.setName(NAME1);

		when(categoryRepository.findByName(NAME1)).thenReturn(cat1);

		CategoryDTO readCategory = categoryService.getCategoryByName(NAME1);

		assertEquals(ID1, readCategory.getId());
		assertEquals(NAME1, readCategory.getName());
	}

}
