package nl.androidappfactory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.androidappfactory.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);

}
