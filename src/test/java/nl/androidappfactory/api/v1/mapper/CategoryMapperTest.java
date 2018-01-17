package nl.androidappfactory.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nl.androidappfactory.api.v1.model.CategoryDTO;
import nl.androidappfactory.domain.Category;

public class CategoryMapperTest {

	public final static Long ID = 1l;
	public final static String NAME = "Fruit";

	private CategoryMapper mapper = CategoryMapper.INSTANCE;

	@Before
	public void setUp() throws Exception {}

	@Test
	public void categoryToCategoryDTO() {

		Category category = new Category();
		category.setId(ID);
		category.setName(NAME);

		CategoryDTO catDTO = mapper.categoryToCategoryDTO(category);

		assertEquals(catDTO.getId(), ID);
		assertEquals(catDTO.getName(), NAME);
	}

	@Test
	public void categoryDTOToCategory() {

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(ID);
		categoryDTO.setName(NAME);

		Category category = mapper.categoryDTOToCategory(categoryDTO);

		assertEquals(category.getId(), ID);
		assertEquals(category.getName(), NAME);
	}

}
