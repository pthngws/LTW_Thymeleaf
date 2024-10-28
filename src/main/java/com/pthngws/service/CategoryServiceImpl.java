package com.pthngws.service;

import com.pthngws.entity.Category;
import com.pthngws.repository.CategoryRepository;
import com.pthngws.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public List<Category> getCategoriesByPage(int page, int pageSize) {
        return categoryRepository.getCategoriesByPage(page, pageSize);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public List<Category> searchCategoriesByName(String keyword) {
        return categoryRepository.searchCategoriesByName(keyword);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.saveCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.updateCategory(category);
    }

    @Override
    public void deleteCategoryById(int id) throws Exception {
        categoryRepository.deleteCategoryById(id);
    }

    @Override
    public int countCategories() {
        return categoryRepository.countCategories();
    }
}
