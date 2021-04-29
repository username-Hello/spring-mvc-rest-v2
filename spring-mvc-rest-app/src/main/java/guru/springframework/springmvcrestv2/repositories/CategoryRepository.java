package guru.springframework.springmvcrestv2.repositories;

import guru.springframework.springmvcrestv2.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String name);
}
