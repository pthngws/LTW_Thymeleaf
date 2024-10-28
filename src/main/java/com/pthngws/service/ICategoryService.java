package com.pthngws.service;

import com.pthngws.entity.Category;
import java.util.List;

public interface ICategoryService {

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
