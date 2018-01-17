package nl.androidappfactory.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.domain.Category;
import nl.androidappfactory.repositories.CategoryRepository;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

	private CategoryRepository categoryRepository;

	public Bootstrap(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Category fruits = new Category();
		fruits.setName("fruits");

		Category dried = new Category();
		fruits.setName("dried");

		Category fresh = new Category();
		fruits.setName("fresh");

		Category exotic = new Category();
		fruits.setName("exotic");

		Category nuts = new Category();
		fruits.setName("nuts");

		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);

		log.info("Data loaaded, #categories: " + categoryRepository.count());
	}

}
