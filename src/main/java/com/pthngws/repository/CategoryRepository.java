package com.pthngws.repository;

import com.pthngws.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> getAllCategories();

    List<Category> getCategoriesByPage(int page, int pageSize);

    Category getCategoryById(int id);

    Category getCategoryByName(String name);

    List<Category> searchCategoriesByName(String keyword);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById(int id) throws Exception;

    int countCategories();
}
