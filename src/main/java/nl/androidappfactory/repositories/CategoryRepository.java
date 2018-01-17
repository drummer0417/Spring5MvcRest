package nl.androidappfactory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.androidappfactory.domain.Category;

/**
 * Created by jt on 9/24/17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
